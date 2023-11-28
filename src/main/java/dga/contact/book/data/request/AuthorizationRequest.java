package dga.contact.book.data.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AuthorizationRequest {
    @Length(max = 50, min = 3, message = "username's value must have min 3 and max 50 length")
    private String username;
    @Length(max = 50, min = 3, message = "password's value must have min 3 and max 50 length")
    private String password;
}
