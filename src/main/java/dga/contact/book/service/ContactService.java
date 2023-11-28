package dga.contact.book.service;

import dga.contact.book.data.dto.ContactDto;
import dga.contact.book.data.mapper.ContactMapper;
import dga.contact.book.data.request.ContactCreateRequest;
import dga.contact.book.data.request.ContactUpdateRequest;
import dga.contact.book.model.Contact;
import dga.contact.book.data.request.ContactSearchRequest;
import dga.contact.book.data.PageData;
import dga.contact.book.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static dga.contact.book.service.AuthorizationService.AuthorizedUser;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public PageData findAll(int page, int size) {
        log.info("incoming request from ContactController");
        Page<Contact> bookPage = contactRepository.findAll(PageRequest.of(page, size));
        return convertToPageData(bookPage);
    }

    public ContactDto find(long id) {
        Optional<Contact> optional = contactRepository.findByIdAndUser(id, AuthorizedUser());
        Contact contact = optional.orElseThrow(() -> new EntityNotFoundException("contact not found"));
        return ContactMapper.MAPPER.contactToContactDto(contact);
    }

    public void create(ContactCreateRequest request) {
        contactRepository.save(
                Contact.builder()
                        .address(request.getAddress())
                        .city(request.getCity())
                        .country(request.getCountry())
                        .mobile(request.getMobile())
                        .email(request.getEmail())
                        .user(AuthorizedUser())
                        .build()
        );
    }

    public void update(Integer id, ContactUpdateRequest request) {
        Contact contact = contactRepository.findByIdAndUser(id, AuthorizedUser())
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));

        if (request.getAddress() != null) contact.setAddress(request.getAddress());
        if (request.getCity() != null) contact.setCity(request.getCity());
        if (request.getCountry() != null) contact.setCountry(request.getCountry());
        if (request.getMobile() != null) contact.setMobile(request.getMobile());
        if (request.getEmail() != null) contact.setEmail(request.getEmail());

        contactRepository.save(contact);
    }

    public void delete(long id) {
        Contact contact = contactRepository.findByIdAndUser(id, AuthorizedUser())
                .orElseThrow(() -> new EntityNotFoundException("contact not found"));
        contactRepository.delete(contact);
    }

    public PageData search(final ContactSearchRequest request, int page, int size) {
        log.info(""" 
            Searching Criteria:
                city={},
                country={},
                email={},
                mobile={},
                address={}
            """,
            request.getCity(),
            request.getCountry(),
            request.getEmail(),
            request.getMobile(),
            request.getAddress()
        );
        Page<Contact> contactPage = contactRepository.findAll(
                Specification
                        .<Contact>where((search, cq, cb) ->
                                cb.equal(search.get("user"), AuthorizedUser())
                        )
                        .<Contact>and(
                                Specification
                                        .<Contact>where((search, cq, cb) ->
                                                cb.like(search.get("city"), "%" + request.getCity() + "%"))
                                        .<Contact>or((search, cq, cb) ->
                                                cb.like(search.get("email"), "%"+request.getEmail()+"%"))
                                        .<Contact>or((search, cq, cb) ->
                                                cb.like(search.get("country"), "%"+request.getCountry()+"%"))
                                        .<Contact>or((search, cq, cb) ->
                                                cb.like(search.get("address"), "%"+request.getAddress()+"%"))
                                        .<Contact>or((search, cq, cb) ->
                                                cb.like(search.get("mobile"), "%"+request.getMobile()+"%"))
                        ),
                PageRequest.of(page, size));

        return convertToPageData(contactPage);
    }

    private PageData convertToPageData(Page<Contact> page) {
        List<ContactDto> list = page.getContent().stream()
                .map(ContactMapper.MAPPER::contactToContactDto)
                .toList();

        return PageData.builder()
                .current(page.getTotalPages())
                .total(page.getTotalElements())
                .data(list)
                .build();
    }
}
