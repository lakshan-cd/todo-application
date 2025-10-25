package com.lakshancd.todo_application_backend.common.validators.numbersizeminmax;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberSizeMinMaxValidator implements ConstraintValidator<NumberSizeMinMax, Object> {
    private String featureName;
    private String fieldName;
    private int min;
    private int max;

    @Override
    public void initialize(NumberSizeMinMax constraintAnnotation) {
        this.featureName = constraintAnnotation.featureName();
        this.fieldName = constraintAnnotation.fieldName();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        int digitCount;
        if (value instanceof Integer) {
            digitCount = String.valueOf(Math.abs((Integer) value)).length();
        } else if (value instanceof Long) {
            digitCount = String.valueOf(Math.abs((Long) value)).length();
        } else if (value instanceof BigInteger) {
            digitCount = String.valueOf(((BigInteger) value).abs()).length();
        } else if (value instanceof BigDecimal){
            digitCount = String.valueOf(((BigDecimal) value).abs()).replace(".", "").length();
        }else {
            return false;
        }

        boolean isValid = digitCount >= min && digitCount <= max;

        if (!isValid) {
            String message = getDynamicMessage();
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }

        return isValid;
    }

    private String getDynamicMessage() {
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
