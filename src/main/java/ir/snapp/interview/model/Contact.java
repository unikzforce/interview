package ir.snapp.interview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contact {
	
	@Id
	@GeneratedValue
	private long id;

}
