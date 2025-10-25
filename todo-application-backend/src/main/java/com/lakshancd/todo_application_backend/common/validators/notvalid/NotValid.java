package com.lakshancd.todo_application_backend.common.validators.notvalid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotValidValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotValid {
    String pattern();
    String featureName();
    String fieldName();

    String message() default "{featureName} {fieldName} is not valid.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
