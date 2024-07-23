package com.org.ems.util.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = SalaryValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SalaryConstraint {
    String message() default "Invalid Salary";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
