package be.switchfully.uno_shark.services;

import org.springframework.stereotype.Component;

@Component
public class GeneralValidationService {

    public void assertNotNullOrBlank(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " can not be empty!");
    }
}
