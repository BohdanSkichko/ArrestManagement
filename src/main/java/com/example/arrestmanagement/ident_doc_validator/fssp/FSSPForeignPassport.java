package com.example.arrestmanagement.ident_doc_validator.fssp;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString

public class FSSPForeignPassport {

    private final String PATTERN = "[A-Z]{6}\\.\\d{2}";
    @Pattern(regexp = "[A-Z]{6}\\.\\d{2}", message = "Please use pattern NNNNNN.SS, where N - Letter, S - Digit")
    private String format;                                             //SS NNNNNN

    public String convertToClientFormat() {
        if (check()) {
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