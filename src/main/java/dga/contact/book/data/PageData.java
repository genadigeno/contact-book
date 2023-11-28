package dga.contact.book.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PageData {
    private long total;
    private int current;
    List<?> data;
}
