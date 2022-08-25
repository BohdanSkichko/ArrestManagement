package com.example.arrestmanagement.validation.handling.fns;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class FNSPassport {
    private final String PATTERN = "\\d{2} \\d{2} [A-Z]{6}";
    private String format;

    public String convertToClientFormat(String format) {
        this.format = format;
        if (this.check()) {
            StringBuilder stringBuilderSS = new StringBuilder(format);
            String SS_SS = String.valueOf(stringBuilderSS.delete(5, 12));

            StringBuilder stringBuilderNNNNN = new StringBuilder(format);
            String NNNNNN = String.valueOf(stringBuilderNNNNN.delete(0, 6));

            return NNNNNN + " " + SS_SS;
        } else throw new IllegalArgumentException("incorrect NumberSeries");
    }

    private boolean check() {
        return format.matches(PATTERN);
    }
}