package com.example.arrestmanagement.validator.ident_doc_validator.fssp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString

public class FSSPPassport {
    private final String PATTERN = "[A-Z]{6}-\\d{4}";

    private String format;

    public String convertToClientFormat(String format) {
        this.format = format;
        if (this.check()) {
            String ss = format;
            StringBuilder stringBuilderSS = new StringBuilder(ss);
            String SS_SS = String.valueOf(stringBuilderSS.delete(0, 7).insert(2, " "));

            String nnnnnn = format;
            StringBuilder stringBuilderNNNNN = new StringBuilder(nnnnnn);
            String NNNNNN = String.valueOf(stringBuilderNNNNN.delete(6, 12));

            return NNNNNN + " " + SS_SS;
        } else throw new RuntimeException("incorrect NumberSeries");
    }

    private boolean check() {
        return format.matches(PATTERN);
    }
}
