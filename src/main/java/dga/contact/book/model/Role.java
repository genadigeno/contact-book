package dga.contact.book.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "roles", schema = "public")
@Getter @Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public static final String USER_ROLE = "ROLE_USER";
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
}