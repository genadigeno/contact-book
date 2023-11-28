package dga.contact.book.controller;

import dga.contact.book.data.dto.ErrorDto;
import dga.contact.book.exception.UserAlreadyExistException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomRestControllerAdvice {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        log.warn(ex.getMessage());
        return buildResponseEntity(ex, ex.getMessage(), 400);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.warn(ex.getMessage());
        return buildResponseEntity(ex, ex.getMessage(), 400);
    }

    private ResponseEntity<Object> buildResponseEntity(RuntimeException ex, String message, int status) {
        return ResponseEntity.badRequest().body(
                ErrorDto.builder()
                        .exception(ex.getMessage())
                        .status(status)
                        .message(message)
                        .build()
        );
    }
}
