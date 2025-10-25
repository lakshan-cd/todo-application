package com.lakshancd.todo_application_backend.common.validators.notblankwithmessage;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotBlankWithMessageValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankWithMessage {
    String fieldName();
    String featureName();

    String message() default "{featureName} {fieldName} is required.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
