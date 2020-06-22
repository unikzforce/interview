package ir.snapp.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import ir.snapp.interview.model.Contact;
import ir.snapp.interview.repository.impl.ContactRepositoryCustom;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, ContactRepositoryCustom, QuerydslPredicateExecutor<Contact> {

}
