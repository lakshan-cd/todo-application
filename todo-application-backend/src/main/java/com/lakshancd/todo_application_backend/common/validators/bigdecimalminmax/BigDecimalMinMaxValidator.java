package com.lakshancd.todo_application_backend.common.validators.bigdecimalminmax;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class BigDecimalMinMaxValidator implements ConstraintValidator<BigDecimalMinMax, BigDecimal> {
    private String featureName;
    private String fieldName;
    private int min;
    private int max;

    @Override
    public void initialize(BigDecimalMinMax constraintAnnotation) {
        this.featureName = constraintAnnotation.featureName();
        this.fieldName = constraintAnnotation.fieldName();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        int length = value.stripTrailingZeros().toPlainString().length();
        boolean isValid = length >= min && length <= max;

        if (!isValid) {
            String message = getDynamicMessage(length);
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }
        return isValid;
    }

    private String getDynamicMessage(int length) {
        if (min != Integer.MIN_VALUE && max != Integer.MAX_VALUE) {
            return ResponseConstants.getMinMaxValidation(featureName, fieldName, min, max);
        } else if (min != Integer.MIN_VALUE) {
            return ResponseConstants.getMinValidation(featureName, fieldName, min);
        } else if (max != Integer.MAX_VALUE) {
            return ResponseConstants.getMaxValidation(featureName, fieldName, max);
        } else {
            return String.format("%s %s has an invalid length.", featureName, fieldName);
        }
    }
}
