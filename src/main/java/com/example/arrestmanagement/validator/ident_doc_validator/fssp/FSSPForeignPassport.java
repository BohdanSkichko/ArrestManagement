package com.example.arrestmanagement.validator.ident_doc_validator.fssp;

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
            String ss = format;
            StringBuilder stringBuilderSS = new StringBuilder(ss);
            String SS = String.valueOf(stringBuilderSS.delete(0, 7));

            String nnnnnn = format;
            StringBuilder stringBuilderNNNNN = new StringBuilder(nnnnnn);
            String NNNNNN = String.valueOf(stringBuilderNNNNN.delete(6, 9));
            return NNNNNN + " " + SS;
        } else throw new RuntimeException("incorrect NumberSeries");
    }

    private boolean check() {
        return format.matches(PATTERN);
    }
}