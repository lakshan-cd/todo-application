package com.lakshancd.todo_application_backend.common.validators.minvalue;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinValueValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinValue {
    String featureName();
    String fieldName();

    String message() default "{featureName} {fieldName} cannot be 0 or minus value.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
