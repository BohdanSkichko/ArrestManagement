package com.example.arrestmanagement.validation.handling.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = DateConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateConstraint {
    String message() default "date pattern yyyy-mm-dd";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


