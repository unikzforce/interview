package ir.snapp.interview.service;

import org.springframework.stereotype.Service;

import ir.snapp.interview.repository.ContactRepository;
import ir.snapp.interview.service.dto.ContactDTO;

@Service
public class ContactService {
	
	private ContactRepository contactRepository;
	
	public ContactService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}
	
	public ContactDTO createContact(ContactDTO newContact) {
		return null;
	}

}
