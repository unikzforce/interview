package ir.snapp.interview.repository.impl;

import java.util.List;

import ir.snapp.interview.model.Contact;

public interface ContactRepositoryCustom {
	
	List<Contact> searchContacts(Contact contact);

}
