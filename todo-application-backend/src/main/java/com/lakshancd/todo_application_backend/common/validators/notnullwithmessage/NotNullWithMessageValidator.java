package com.lakshancd.todo_application_backend.common.validators.notnullwithmessage;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotNullWithMessageValidator implements ConstraintValidator<NotNullWithMessage, Object> {
    private String fieldName;
    private String featureName;

    @Override
    public void initialize(NotNullWithMessage constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
        this.featureName = constraintAnnotation.featureName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ResponseConstants.getRequiredMessage(featureName, fieldName))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
