package com.lakshancd.todo_application_backend.common.validators.decimalminvalue;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DecimalMinValueValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalMinValue {
    String featureName();
    String fieldName();
    String min();

    String message() default "{featureName} {fieldName} cannot be less than {min}.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    boolean minInclusive() default true;
}
