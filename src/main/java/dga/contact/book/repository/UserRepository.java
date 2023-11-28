package dga.contact.book.repository;

import dga.contact.book.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);

    @EntityGraph("user-eg")
    Page<User> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);
}
