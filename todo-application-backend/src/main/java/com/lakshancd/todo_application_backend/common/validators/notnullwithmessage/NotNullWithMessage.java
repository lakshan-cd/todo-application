package com.lakshancd.todo_application_backend.common.validators.notnullwithmessage;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotNullWithMessageValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullWithMessage {
    String fieldName();
    String featureName();

    String message() default "{featureName} {fieldName} is required.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
