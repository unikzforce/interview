package ir.snapp.interview.repository.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.IteratorUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import ir.snapp.interview.model.Contact;
import ir.snapp.interview.model.QContact;
import ir.snapp.interview.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactRepositoryImpl implements ContactRepositoryCustom {

	@Autowired
	private ContactRepository contactRepository;

//	private final EntityManager entityManager;
	
	public ContactRepositoryImpl(/*ContactRepository contactRepository*/) {
//		this.contactRepository = contactRepository;
	}


	@Override
	public List<Contact> searchContacts(Contact contact) {
		QContact contactQuery = QContact.contact;
		
		BooleanBuilder booleanPredBuilder = new BooleanBuilder();
		
		if( contact.getEmail() != null )
			booleanPredBuilder.and(contactQuery.email.eq(contact.getEmail()));
		
		if( contact.getGithub() != null )
			booleanPredBuilder.and(contactQuery.github.eq(contact.getGithub()));
		
		if( contact.getName() != null )
			booleanPredBuilder.and(contactQuery.name.eq(contact.getName()));
		
		if( contact.getOrganization() != null )
			booleanPredBuilder.and(contactQuery.organization.eq(contact.getOrganization()));
		
		if( contact.getPhoneNumber() != null )
			booleanPredBuilder.and(contactQuery.phoneNumber.eq(contact.getPhoneNumber()));

		return IterableUtils.toList(contactRepository.findAll(booleanPredBuilder));
	}

}
