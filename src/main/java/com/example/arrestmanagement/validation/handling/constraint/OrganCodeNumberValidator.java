package com.example.arrestmanagement.validation.handling.constraint;
import com.example.arrestmanagement.dictionary.ArrestOrganCodeEnum;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class OrganCodeNumberValidator implements
        ConstraintValidator<OrganCodeConstraint, Integer> {

    @Override
    public void initialize(OrganCodeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer codeNumber, ConstraintValidatorContext constraintValidatorContext) {
        return ArrestOrganCodeEnum.isCorrectCode(codeNumber);
    }
}
