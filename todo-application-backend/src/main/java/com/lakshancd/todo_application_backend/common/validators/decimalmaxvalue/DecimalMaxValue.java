package com.lakshancd.todo_application_backend.common.validators.decimalmaxvalue;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DecimalMaxValueValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalMaxValue {
    String featureName();
    String fieldName();
    String max();

    String message() default "{featureName} {fieldName} cannot exceed {max}.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    boolean maxInclusive() default true;
}
