package com.org.ems.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SalaryValidator implements ConstraintValidator<SalaryConstraint, Double> {

    @Override
    public void initialize(SalaryConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Double salary, ConstraintValidatorContext constraintValidatorContext) {
        return salary > 0;
    }
}
