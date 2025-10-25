package com.lakshancd.todo_application_backend.common.validators.minvalue;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinValueValidator implements ConstraintValidator<MinValue, Integer> {
    private String featureName;
    private String fieldName;

    @Override
    public void initialize(MinValue constraintAnnotation) {
        this.featureName = constraintAnnotation.featureName();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }

        boolean valid = value > 0;

        if(!valid){
            String message = ResponseConstants.getMinValueMessage(featureName, fieldName);
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }
        return valid;
    }
}
