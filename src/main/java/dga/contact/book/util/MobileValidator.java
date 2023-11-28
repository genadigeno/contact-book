package dga.contact.book.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class MobileValidator implements ConstraintValidator<Mobile, String> {
    private final String REGEX = "(?:\\+9955|\\b5)(\\d{8})";

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        log.info("mobile number : {}", mobile);
        return Pattern.compile(REGEX).matcher(mobile).matches();
    }
}
