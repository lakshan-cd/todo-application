package com.lakshancd.todo_application_backend.common.validators.notblankwithmessage;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class NotBlankWithMessageValidator implements ConstraintValidator<NotBlankWithMessage, String> {
    private String fieldName;
    private String featureName;

    @Override
    public void initialize(NotBlankWithMessage constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
        this.featureName = constraintAnnotation.featureName();
    }
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.hasText(s)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ResponseConstants.getRequiredMessage(featureName, fieldName))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}