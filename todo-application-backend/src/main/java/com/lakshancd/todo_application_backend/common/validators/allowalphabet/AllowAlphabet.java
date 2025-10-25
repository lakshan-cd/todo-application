package com.lakshancd.todo_application_backend.common.validators.allowalphabet;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AllowAlphabetValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowAlphabet {
    String pattern();
    String featureName();
    String fieldName();

    String message() default "{featureName} {fieldName} should not contain special characters or numbers.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
