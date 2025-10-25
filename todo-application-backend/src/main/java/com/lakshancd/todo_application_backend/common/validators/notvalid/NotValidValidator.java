package com.lakshancd.todo_application_backend.common.validators.notvalid;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotValidValidator implements ConstraintValidator<NotValid, String> {
    private String pattern;
    private String featureName;
    private String fieldName;

    @Override
    public void initialize(NotValid constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
        this.featureName = constraintAnnotation.featureName();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        boolean valid = value.matches(pattern);

        if(!valid){
            String message = ResponseConstants.getNotValidMessage(featureName, fieldName);
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }
        return valid;
    }
}
