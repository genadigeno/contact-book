package dga.contact.book.controller;

import dga.contact.book.data.dto.ContactDto;
import dga.contact.book.data.dto.DefaultErrorResponseBody;
import dga.contact.book.data.dto.ErrorDto;
import dga.contact.book.data.request.ContactCreateRequest;
import dga.contact.book.data.request.ContactSearchRequest;
import dga.contact.book.data.PageData;
import dga.contact.book.data.request.ContactUpdateRequest;
import dga.contact.book.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Contact", description = "Contact REST API")
@Slf4j
@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @Operation(summary = "get all Contacts with pagination. Secured by Bearer token")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", content={@Content(schema=@Schema(implementation=PageData.class))}),
            @ApiResponse(responseCode="400", content={@Content(schema=@Schema(implementation=ErrorDto.class))}),
            @ApiResponse(responseCode="401", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))}),
            @ApiResponse(responseCode="500", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))})})
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public PageData getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                           @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
        log.info("incoming request from a client");
        return contactService.findAll(page, size);
    }

    @Operation(summary = "get Contact by id. Secured by Bearer token")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", content={@Content(schema=@Schema(implementation=ContactDto.class))}),
            @ApiResponse(responseCode="400", content={@Content(schema=@Schema(implementation=ErrorDto.class))}),
            @ApiResponse(responseCode="401", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))}),
            @ApiResponse(responseCode="403", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))}),
            @ApiResponse(responseCode="500", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))})})
    @PreAuthorize("isAuthenticated() and @authorizationService.hasAccessOnContact(#id)")
    @GetMapping("/{id}")
    public ContactDto getContact(@PathVariable Integer id){
        return contactService.find(id);
    }

    @Operation(summary = "create a Contact. Secured by Bearer token")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", content={@Content(schema=@Schema(implementation=String.class))}),
            @ApiResponse(responseCode="400", content={@Content(schema=@Schema(implementation=ErrorDto.class))}),
            @ApiResponse(responseCode="401", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))}),
            @ApiResponse(responseCode="500", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))})})
    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public String createContact(@Validated @RequestBody ContactCreateRequest createRequest){
        contactService.create(createRequest);
        return "contact created";
    }

    @Operation(summary = "update a Contact by id. Secured by Bearer token")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", content={@Content(schema=@Schema(implementation=String.class))}),
            @ApiResponse(responseCode="400", content={@Content(schema=@Schema(implementation=ErrorDto.class))}),
            @ApiResponse(responseCode="401", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))}),
            @ApiResponse(responseCode="403", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))}),
            @ApiResponse(responseCode="500", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))})})
    @PreAuthorize("isAuthenticated() and @authorizationService.hasAccessOnContact(#id)")
    @PostMapping("/{id}")
    public String updateContact(@Validated @RequestBody ContactUpdateRequest updateRequest, @PathVariable Integer id){
        contactService.update(id, updateRequest);
        return "contact updated";
    }

    @Operation(summary = "delete a Contact by id. Secured by Bearer token")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", content={@Content(schema=@Schema(implementation=String.class))}),
            @ApiResponse(responseCode="400", content={@Content(schema=@Schema(implementation=ErrorDto.class))}),
            @ApiResponse(responseCode="401", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))}),
            @ApiResponse(responseCode="403", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))}),
            @ApiResponse(responseCode="500", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))})})
    @PreAuthorize("isAuthenticated() and @authorizationService.hasAccessOnContact(#id)")
    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable Integer id){
        contactService.delete(id);
        return "contact deleted";
    }

    /* If we want to skip specific fields they should have null values */
    @Operation(summary = "search the Contacts with pagination. Secured by Bearer token", tags = {"Search"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", content={@Content(schema=@Schema(implementation=PageData.class))}),
            @ApiResponse(responseCode="400", content={@Content(schema=@Schema(implementation=ErrorDto.class))}),
            @ApiResponse(responseCode="401", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))}),
            @ApiResponse(responseCode="500", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))})})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search")
    public PageData searchContacts(@Validated @RequestBody ContactSearchRequest searchRequest,
                           @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                           @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
        return contactService.search(searchRequest, page, size);
    }
}
