package ir.snapp.interview.service;

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

}
