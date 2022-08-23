package com.example.arrestmanagement.validator.ident_doc_validator.fns;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString

public class FNSPassport {
    private final String PATTERN = "\\d{2} \\d{2} [A-Z]{6}";
    private String format;

    public String convertToClientFormat(String format) {
        this.format = format;
        if (this.check()) {
            String ss_ss = format;
            StringBuilder stringBuilderSS = new StringBuilder(ss_ss);
            String SS_SS = String.valueOf(stringBuilderSS.delete(5, 12));

            String nnnnnn = format;
            StringBuilder stringBuilderNNNNN = new StringBuilder(nnnnnn);
            String NNNNNN = String.valueOf(stringBuilderNNNNN.delete(0, 6));

            return NNNNNN + " " + SS_SS;
        } else throw new RuntimeException("incorrect NumberSeries");
    }

    private boolean check() {
        return format.matches(PATTERN);
    }
}