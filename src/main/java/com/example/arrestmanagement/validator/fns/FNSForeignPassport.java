package com.example.arrestmanagement.validator.fns;

import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString

public class FNSForeignPassport {
    private final String PATTERN = "\\d{2} [A-Z]{6}";
    private String format;


    public String convertToClientFormat(String format) {
        this.format = format;
        if (this.check()) {
            StringBuilder stringBuilderSS = new StringBuilder(format);
            String SS = String.valueOf(stringBuilderSS.delete(2, 9));

            StringBuilder stringBuilderNNNNN = new StringBuilder(format);
            String NNNNNN = String.valueOf(stringBuilderNNNNN.delete(0, 3));

            return NNNNNN + " " + SS;
        } else {
            throw new IllegalArgumentException("incorrect NumberSeries");
        }
    }

    private boolean check() {
        return format.matches(PATTERN);
    }
}