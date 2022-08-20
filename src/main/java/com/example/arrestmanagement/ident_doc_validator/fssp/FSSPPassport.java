package com.example.arrestmanagement.ident_doc_validator.fssp;

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
//    @Pattern(regexp = "[A-Z]{6}-\\d{4}", message = "Please use pattern NNNNNN-SSSS, where N - Letter, S - Digit")
    private String format;                                           //NNNNNN SS SS

    public String convertToClientFormat() {
        if (check()) {
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
