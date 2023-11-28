package dga.contact.book.data.mapper;

import dga.contact.book.data.dto.ContactDto;
import dga.contact.book.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {
    ContactMapper MAPPER = Mappers.getMapper(ContactMapper.class);
    ContactDto contactToContactDto(Contact source);
}
