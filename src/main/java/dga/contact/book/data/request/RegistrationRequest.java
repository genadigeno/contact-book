package dga.contact.book.data.request;

import dga.contact.book.util.PasswordMatches;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@PasswordMatches
@Builder
public class RegistrationRequest {
    @Length(max = 50, min = 3, message = "username's value must have min 3 and max 50 length")
    private String username;
    @Length(max = 50, min = 3, message = "password's value must have min 8 and max 50 length")
    private String password;
    @Length(max = 50, min = 3, message = "repeatedPassword's value must have min 8 and max 50 length")
    private String repeatedPassword;
    @NotNull
    @Length(max = 50, message = "firstname's length is too long")
    private String firstname;
    @NotNull
    @Length(max = 50, message = "lastname's length is too long")
    private String lastname;
}
