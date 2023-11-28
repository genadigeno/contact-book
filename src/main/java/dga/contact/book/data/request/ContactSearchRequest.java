package dga.contact.book.data.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class ContactSearchRequest {
    @Length(max = 50, message = "mobile's value must have max 50 length")
    private String mobile;

    @Length(max = 50, message = "mobile's value must have max 50 length")
    private String email;

    @Length(max = 30, message = "city's value must have max 50 length")
    private String city;

    @Length(max = 20, message = "country's value must have max 50 length")
    private String country;

    @Length(max = 50, message = "address's value must have max 50 length")
    private String address;
}
