package com.lakshancd.todo_application_backend.common.validators.hasextension;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HasExtensionValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasExtension {
    String message() default "File name must have an extension.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
