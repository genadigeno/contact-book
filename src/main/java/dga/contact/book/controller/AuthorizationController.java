package dga.contact.book.controller;

import dga.contact.book.data.dto.DefaultErrorResponseBody;
import dga.contact.book.data.dto.ErrorDto;
import dga.contact.book.data.request.AuthorizationRequest;
import dga.contact.book.data.dto.AuthorizationDto;
import dga.contact.book.data.request.RegistrationRequest;
import dga.contact.book.service.AuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "Authentication REST API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @Operation(summary = "register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", content={@Content(schema=@Schema(implementation=AuthorizationDto.class))}),
            @ApiResponse(responseCode="400", content={@Content(schema=@Schema(implementation=ErrorDto.class))}),
            @ApiResponse(responseCode="500", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))})})
    @PostMapping("/register")
    public AuthorizationDto register(@Validated @RequestBody RegistrationRequest request){
        return authorizationService.register(request);
    }

    @Operation(summary = "login a registered user")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", content={@Content(schema=@Schema(implementation=AuthorizationDto.class))}),
            @ApiResponse(responseCode="400", content={@Content(schema=@Schema(implementation=ErrorDto.class))}),
            @ApiResponse(responseCode="500", content={@Content(schema=@Schema(implementation=DefaultErrorResponseBody.class))})})
    @PostMapping("/login")
    public AuthorizationDto login(@Validated @RequestBody AuthorizationRequest request){
        return authorizationService.login(request);
    }
}
