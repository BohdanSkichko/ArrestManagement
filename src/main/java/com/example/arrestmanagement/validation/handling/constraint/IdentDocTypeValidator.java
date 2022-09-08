package com.example.arrestmanagement.validation.handling.constraint;

import com.example.arrestmanagement.helper.ArrestFNSPassportCodeEnum;
import com.example.arrestmanagement.helper.ArrestFSSPPassportCodeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class IdentDocTypeValidator implements ConstraintValidator<IdentDocTypeConstraint, Integer> {

    @Override
    public void initialize(IdentDocTypeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer type, ConstraintValidatorContext constraintValidatorContext) {
        return ArrestFSSPPassportCodeEnum.isCorrectCode(type) ||
                ArrestFNSPassportCodeEnum.isCorrectCode(type);
    }
}
