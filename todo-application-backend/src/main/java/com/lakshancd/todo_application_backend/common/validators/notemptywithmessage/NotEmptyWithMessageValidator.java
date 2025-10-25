package com.lakshancd.todo_application_backend.common.validators.notemptywithmessage;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;
import java.util.Map;

public class NotEmptyWithMessageValidator implements ConstraintValidator<NotEmptyWithMessage, Object> {
    private String fieldName;

    @Override
    public void initialize(NotEmptyWithMessage constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            String message = ResponseConstants.getAtLeastOneRequiredMessage(fieldName);
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }
        if (value instanceof CharSequence) {
            return !((CharSequence) value).isEmpty();
        } else if (value instanceof Collection) {
            return !((Collection<?>) value).isEmpty();
        } else if (value instanceof Map) {
            return !((Map<?, ?>) value).isEmpty();
        } else if (value.getClass().isArray()) {
            return ((Object[]) value).length > 0;
        }

        return true;
    }
}
