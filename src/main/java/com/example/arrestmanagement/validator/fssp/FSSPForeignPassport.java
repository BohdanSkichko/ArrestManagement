package com.example.arrestmanagement.validator.fssp;

import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FSSPForeignPassport {

    private final String PATTERN = "[A-Z]{6}\\.\\d{2}";

    private String format;

    public String convertToClientFormat(String format) {
        this.format = format;
        if (this.check()) {
            StringBuilder stringBuilderSS = new StringBuilder(format);
            String SS = String.valueOf(stringBuilderSS.delete(0, 7));

            StringBuilder stringBuilderNNNNN = new StringBuilder(format);
            String NNNNNN = String.valueOf(stringBuilderNNNNN.delete(6, 9));
            return NNNNNN + " " + SS;
        } else throw new IllegalArgumentException("incorrect NumberSeries");
    }

    private boolean check() {
        return format.matches(PATTERN);
    }
}