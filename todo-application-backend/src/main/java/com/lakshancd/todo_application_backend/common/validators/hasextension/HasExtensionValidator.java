package com.lakshancd.todo_application_backend.common.validators.hasextension;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HasExtensionValidator implements ConstraintValidator<HasExtension, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) return true; // Let @NotNull handle null/empty
        int lastDot = value.lastIndexOf('.');
        return lastDot > 0 && lastDot < value.length() - 1;
    }
}
