package com.example.arrestmanagement.validation.handling.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DateConstraintValidator implements ConstraintValidator<DateConstraint, Date> {
    private final static String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public void initialize(DateConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        try {
//            dateFormat.setLenient(false);
            Date current = dateFormat.parse(String.valueOf(date));
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
