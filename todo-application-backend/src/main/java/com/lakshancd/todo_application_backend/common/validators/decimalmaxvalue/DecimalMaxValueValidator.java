package com.lakshancd.todo_application_backend.common.validators.decimalmaxvalue;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class DecimalMaxValueValidator implements ConstraintValidator<DecimalMaxValue, BigDecimal> {
    private String featureName;
    private String fieldName;
    private BigDecimal maxValue;

    @Override
    public void initialize(DecimalMaxValue constraintAnnotation) {
        this.featureName = constraintAnnotation.featureName();
        this.fieldName = constraintAnnotation.fieldName();
        this.maxValue = new BigDecimal(constraintAnnotation.max());
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null || value.compareTo(maxValue) <= 0) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                        ResponseConstants.getDecimalMaxValidation(featureName, fieldName, maxValue))
                .addConstraintViolation();
        return false;
    }
}
