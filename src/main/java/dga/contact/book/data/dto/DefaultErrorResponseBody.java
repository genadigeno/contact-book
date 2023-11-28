package dga.contact.book.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DefaultErrorResponseBody {
    private Date timestamp;
    private int status;
    private String error;
    private String path;
}
