package dga.contact.book.repository;

import dga.contact.book.model.Contact;
import dga.contact.book.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {
    Optional<Contact> findByIdAndUser(long id, User user);

    @EntityGraph("contact-eg")
    Page<Contact> findAll(Specification<Contact> specification, Pageable pageable);
}
