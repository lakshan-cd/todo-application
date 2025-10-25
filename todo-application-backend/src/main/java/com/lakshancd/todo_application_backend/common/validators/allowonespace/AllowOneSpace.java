package com.lakshancd.todo_application_backend.common.validators.allowonespace;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AllowOneSpaceValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowOneSpace {
    String pattern();
    String featureName();
    String fieldName();

    String message() default "{featureName} {fieldName} can only contain one middle space.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
