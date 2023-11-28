package dga.contact.book.data.request;

import dga.contact.book.util.Mobile;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactUpdateRequest {
    @Mobile
    @Length(max = 50, min = 3, message = "mobile's value must have min 3 and max 50 length")
    private String mobile;

    @Email
    private String email;

    @Length(max = 30, min = 2, message = "city's value must have min 2 and max 50 length")
    private String city;

    @Length(max = 20, min = 2, message = "country's value must have min 2 and max 50 length")
    private String country;

    @Length(max = 50, min = 2, message = "address's value must have min 2 and max 50 length")
    private String address;
}
