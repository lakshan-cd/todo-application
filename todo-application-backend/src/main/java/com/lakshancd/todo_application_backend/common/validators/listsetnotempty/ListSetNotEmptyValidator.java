package com.lakshancd.todo_application_backend.common.validators.listsetnotempty;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;

public class ListSetNotEmptyValidator implements ConstraintValidator<ListSetNotEmpty, Collection<Integer>> {
    private String fieldName;
    private String featureName;

    @Override
    public void initialize(ListSetNotEmpty constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
        this.featureName = constraintAnnotation.featureName();
    }

    @Override
    public boolean isValid(Collection<Integer> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            String message = ResponseConstants.getSelectAtLeastOne(featureName, fieldName);
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
