package com.example.arrestmanagement.validation.handling.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrganCodeNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OrganCodeConstraint {
    String message() default "Invalid organCode";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
