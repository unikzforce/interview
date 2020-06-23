package ir.snapp.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ir.snapp.interview.model.Contact;
import ir.snapp.interview.model.GitRepo;

public interface GitRepoRepository extends JpaRepository<GitRepo, Long> {
	
	void deleteAllByContact(Contact contact);

}
