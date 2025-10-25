package com.lakshancd.todo_application_backend.common.validators.allownumeric;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AllowNumericValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowNumeric {
    String pattern();
    String featureName();
    String fieldName();

    String message() default "{featureName} {fieldName} should be numeric.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
