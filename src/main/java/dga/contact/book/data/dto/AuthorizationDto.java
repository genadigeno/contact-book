package dga.contact.book.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationDto {
    private String jwtToken;
    private String jwtRefreshToken;
    private String message;
    private int status;
}
