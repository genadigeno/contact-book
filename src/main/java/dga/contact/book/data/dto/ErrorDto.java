package dga.contact.book.data.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
@Builder
public class ErrorDto {
    private int status;
    private String exception;
    private String message;
    private List<FieldError> errors;
}
