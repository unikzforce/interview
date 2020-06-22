package ir.snapp.interview.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.snapp.interview.service.ContactService;
import ir.snapp.interview.service.dto.ContactDTO;

@RestController
@RequestMapping("/contacts")
public class ContactResource {
	
	private final ContactService contactService;
	
	public ContactResource(ContactService contactService) {
		this.contactService = contactService;
	}
	
	public ResponseEntity<ContactDTO> yes() {
		
		return null;
	}

}
