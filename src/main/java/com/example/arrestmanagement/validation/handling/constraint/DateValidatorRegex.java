package com.example.arrestmanagement.validation.handling.constraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DateValidatorRegex {

    private static final String DATE_PATTERN =
            "^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

    private static final Pattern pattern = Pattern.compile(DATE_PATTERN);

    public static boolean isValid(final String date) {

        boolean result = false;

        Matcher matcher = pattern.matcher(date);

        if (matcher.matches()) {

            result = true;
            int year = Integer.parseInt(matcher.group(1));

            String month = matcher.group(2);
            String day = matcher.group(3);

            if ((month.equals("4") || month.equals("6") || month.equals("9") ||
                    month.equals("04") || month.equals("06") || month.equals("09") ||
                    month.equals("11")) && day.equals("31")) {
                result = false;
            } else if (month.equals("2") || month.equals("02")) {
                if (day.equals("30") || day.equals("31")) {
                    result = false;
                } else if (day.equals("29")) {  // leap year? feb 29 days.
                    if (!isLeapYear(year)) {
                        result = false;
                    }
                }
            }

        }

        return result;
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
}