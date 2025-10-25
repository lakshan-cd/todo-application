package com.lakshancd.todo_application_backend.common.validators.sizeminmax;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SizeMinMaxValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SizeMinMax {
    String featureName();
    String fieldName();
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;

    String message() default "{featureName} {fieldName} should have {min} or more characters and cannot exceed {max} characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
