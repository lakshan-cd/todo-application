package com.lakshancd.todo_application_backend.common.validators.decimalminvalue;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class DecimalMinValueValidator implements ConstraintValidator<DecimalMinValue, BigDecimal> {

    private String featureName;
    private String fieldName;
    private BigDecimal minValue;

    @Override
    public void initialize(DecimalMinValue constraintAnnotation) {
        this.featureName = constraintAnnotation.featureName();
        this.fieldName = constraintAnnotation.fieldName();
        this.minValue = new BigDecimal(constraintAnnotation.min());
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null || value.compareTo(minValue) >= 0) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                        ResponseConstants.getDecimalMinValidation(featureName, fieldName, minValue))
                .addConstraintViolation();
        return false;
    }
}