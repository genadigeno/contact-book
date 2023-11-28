package dga.contact.book.service;

import dga.contact.book.data.request.AuthorizationRequest;
import dga.contact.book.data.dto.AuthorizationDto;
import dga.contact.book.data.request.RegistrationRequest;
import dga.contact.book.data.mapper.UserMapper;
import dga.contact.book.exception.UserAlreadyExistException;
import dga.contact.book.jwt.JwtTokenUtil;
import dga.contact.book.model.Contact;
import dga.contact.book.model.Role;
import dga.contact.book.model.User;
import dga.contact.book.repository.ContactRepository;
import dga.contact.book.repository.RoleRepository;
import dga.contact.book.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ContactRepository contactRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthorizationDto register(RegistrationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())){
            throw new UserAlreadyExistException();
        }

        User user = UserMapper.MAPPER.registrationRequestToUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setExpired(false);
        user.setLocked(false);

        //assign user role
        Role role = roleRepository.findByName(Role.USER_ROLE)
                .orElseThrow(() -> new EntityNotFoundException("role not found"));
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        return AuthorizationDto.builder()
                .message("Successful registration")
                .status(200)
                .build();
    }

    public boolean hasAccessOnContact(long contactId){
        Optional<Contact> optional = contactRepository.findById(contactId);
        return optional.map(contact -> contact.getUser().getUsername().equals(AuthorizedUser().getUsername()))
                .orElse(true);
    }

    public AuthorizationDto login(AuthorizationRequest request) {
        log.info("incoming auth request for user : {}", request.getUsername());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));

        return AuthorizationDto.builder()
                .jwtToken(jwtTokenUtil.generateToken(user.getUsername()))
                .build();
    }

    public static User AuthorizedUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof User user) {
            return user;
        }

        throw new UsernameNotFoundException("user not found");
    }
}
