package com.lakshancd.todo_application_backend.common.validators.specialcharacters;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SpecialCharacterValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecialCharacter {
    String pattern();
    String featureName();
    String fieldName();

    String message() default "{featureName} {fieldName} should not be contain special characters.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
