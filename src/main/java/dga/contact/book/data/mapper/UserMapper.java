package dga.contact.book.data.mapper;

import dga.contact.book.data.request.RegistrationRequest;
import dga.contact.book.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    User registrationRequestToUser(RegistrationRequest destination);
}
