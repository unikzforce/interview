package ir.snapp.interview.service.mapper;

import org.mapstruct.Mapper;

import ir.snapp.interview.model.Contact;
import ir.snapp.interview.service.dto.ContactDTO;

@Mapper(componentModel = "spring", uses = {})
public abstract class ContactMapper {
	
	public abstract ContactDTO convertToDTO(Contact contact);
	
	public abstract Contact covertToDomain(ContactDTO contactDTO);

}
