package com.lakshancd.todo_application_backend.common.validators.numbersizeminmax;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NumberSizeMinMaxValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberSizeMinMax {
    String featureName();
    String fieldName();

    String message() default "{featureName} {fieldName} should have {min} or more number characters and cannot exceed {max} number characters.\"";

    Class<?>[] groups() default  {};
    Class<? extends Payload>[] payload() default {};

    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
}
