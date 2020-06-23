package ir.snapp.interview.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.kohsuke.github.GHRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.snapp.interview.model.Contact;
import ir.snapp.interview.model.GitRepo;
import ir.snapp.interview.repository.ContactRepository;
import ir.snapp.interview.repository.GitRepoRepository;
import ir.snapp.interview.service.dto.ContactDTO;
import ir.snapp.interview.service.mapper.ContactMapper;

@Service
public class ContactService {
	
	private final ContactRepository contactRepository;
	
	private final GitRepoRepository gitRepoRepository;
	
	private final ContactMapper contactMapper;
	
	private final GithubService githubService;
	
	public ContactService(
			ContactRepository contactRepository,
			ContactMapper contactMapper,
			GitRepoRepository gitRepoRepository,
			GithubService githubService
			) {
		this.contactRepository = contactRepository;
		this.contactMapper = contactMapper;
		this.gitRepoRepository = gitRepoRepository;
		this.githubService = githubService;
	}
	
	@Transactional
	public ContactDTO createContact(ContactDTO newContactDTO) {
		Contact savedContact = contactRepository.save( contactMapper.convertToDomain(newContactDTO));
		
		
		return contactMapper.convertToDTO(savedContact);
	}
	
	@Transactional
	public void enrichContactWithGitReposIdempotently(Contact contact) {
		// This Action must be Idempotent
		// So First we delete all exisiting GitRepos
		// And then add again add GitRepos
		List<GHRepository> ghRepos = githubService.getGHRepositoriesByUsername(contact.getGithub());
		
		List<GitRepo> newRepos = new ArrayList<GitRepo>(ghRepos.size());
		
		ghRepos.forEach( ghRepo -> {
			GitRepo gr = new GitRepo();
			gr.setContact(contact);
			gr.setUrl(ghRepo.getHtmlUrl().toString());
			
			newRepos.add(gr);
		});
		
		
		gitRepoRepository.deleteAllByContact(contact);
		
		gitRepoRepository.saveAll(newRepos);
		
	}

	
	public List<ContactDTO> searchContacts(ContactDTO contactDTO) {
		// TODO Auto-generated method stub
		Example<Contact> contactExample = Example.of(contactMapper.convertToDomain(contactDTO));
		
		List<Contact> foundContacts = contactRepository.findAll(contactExample);
		
		
		return foundContacts
				.stream()
				.map(contact -> contactMapper.convertToDTO(contact))
				.collect(Collectors.toList());
	}
	
	public List<ContactDTO> searchContactsQueryDSL(ContactDTO contactDTO) {
		
		Contact contact = contactMapper.convertToDomain(contactDTO);
		
		
		List<Contact> foundContacts = contactRepository.searchContacts(contact);
		
		
		return foundContacts
				.stream()
				.map(contact -> contactMapper.convertToDTO(contact))
				.collect(Collectors.toList());
	}

}
