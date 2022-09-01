package com.example.arrestmanagement.validation.handling.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdentDocTypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdentDocTypeConstraint {
    String message() default "Invalid IdentDoc Type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


