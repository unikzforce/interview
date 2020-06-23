package ir.snapp.interview.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import ir.snapp.interview.model.Contact;
import ir.snapp.interview.repository.ContactRepository;
import ir.snapp.interview.service.dto.ContactDTO;
import ir.snapp.interview.service.mapper.ContactMapper;

@Service
public class ContactService {
	
	private final ContactRepository contactRepository;
	
	private final ContactMapper contactMapper;
	
	public ContactService(
			ContactRepository contactRepository,
			ContactMapper contactMapper
			) {
		this.contactRepository = contactRepository;
		this.contactMapper = contactMapper;
	}
	
	public ContactDTO createContact(ContactDTO newContactDTO) {
		Contact savedContact = contactRepository.save( contactMapper.convertToDomain(newContactDTO));
		
		
		return contactMapper.convertToDTO(savedContact);
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
		
		
		return null;
	}

}
