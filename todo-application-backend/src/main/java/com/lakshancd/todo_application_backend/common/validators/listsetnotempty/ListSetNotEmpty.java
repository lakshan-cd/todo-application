package com.lakshancd.todo_application_backend.common.validators.listsetnotempty;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ListSetNotEmptyValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ListSetNotEmpty {
    String fieldName();
    String featureName();
    String message() default "At least one element must be selected";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}