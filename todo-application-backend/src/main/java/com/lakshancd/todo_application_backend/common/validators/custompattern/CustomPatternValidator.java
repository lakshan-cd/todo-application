package com.lakshancd.todo_application_backend.common.validators.custompattern;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class CustomPatternValidator implements ConstraintValidator<CustomPattern, String> {
    private String pattern;
    private String featureName;
    private String fieldName;
    private String key;

    @Override
    public void initialize(CustomPattern constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
        this.featureName = constraintAnnotation.featureName();
        this.fieldName = constraintAnnotation.fieldName();
        this.key = constraintAnnotation.key();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        boolean valid = value.matches(pattern);

        if(!valid){
            String message = getDynamicMessage(key);
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }
        return valid;
    }

    private String getDynamicMessage(String key) {
        if (Objects.equals(key, "VatId")) {
            return ResponseConstants.getVatIdErrorMessage(featureName, fieldName);
        } else if (Objects.equals(key, "BusinessId")) {
            return ResponseConstants.getBusinessIdErrorMessage(featureName, fieldName);
        } else if(Objects.equals(key, "AllowOneMiddle")){
            return ResponseConstants.getCannotHaveMiddleSpaceAndAllowAlphabetOnly(featureName, fieldName);
        }else {
            return String.format("%s %s is invalid.", featureName, fieldName);
        }
    }
}
