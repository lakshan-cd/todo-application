package com.lakshancd.todo_application_backend.common.validators.allowonespace;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AllowOneSpaceValidator implements ConstraintValidator<AllowOneSpace, String> {
    private String pattern;
    private String featureName;
    private String fieldName;

    @Override
    public void initialize(AllowOneSpace constraintAnnotation) {
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
            String message = ResponseConstants.getOneSpaceAllowedMessage(featureName, fieldName);
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }
        return valid;
    }
}
