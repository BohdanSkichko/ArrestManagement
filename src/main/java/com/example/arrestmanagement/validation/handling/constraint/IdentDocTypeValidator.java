package com.example.arrestmanagement.validation.handling.constraint;

import com.example.arrestmanagement.helper.PassportsCodPropertiesEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class IdentDocTypeValidator implements ConstraintValidator<IdentDocTypeConstraint, Integer> {

    @Override
    public void initialize(IdentDocTypeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer type, ConstraintValidatorContext constraintValidatorContext) {
        return type == Integer.parseInt(PassportsCodPropertiesEnum.FNS_PASSPORT.getPath()) ||
                type == Integer.parseInt(PassportsCodPropertiesEnum.FNS_FOREIGN_PASSPORT.getPath()) ||
                type == Integer.parseInt(PassportsCodPropertiesEnum.FSSP_PASSPORT.getPath()) ||
                type == Integer.parseInt(PassportsCodPropertiesEnum.FSSP_FOREIGN_PASSPORT.getPath());
    }
}
