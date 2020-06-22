package ir.snapp.interview.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping()
	public ResponseEntity<ContactDTO> createContact(@RequestBody ContactDTO newContactDTO) {
		ContactDTO savedContact = contactService.createContact(newContactDTO);
		
		return ResponseEntity.ok(savedContact);
	}

}
