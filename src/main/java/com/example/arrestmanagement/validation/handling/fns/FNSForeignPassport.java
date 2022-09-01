package com.example.arrestmanagement.validation.handling.fns;

import com.example.arrestmanagement.exception.handling.ArrestIncorrectException;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class FNSForeignPassport {
    private static final String PATTERN = "\\d{2} [A-Z]{6}";


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
            throw new ArrestIncorrectException("incorrect NumberSeries");
        }
    }

    private boolean check() {
        return format.matches(PATTERN);
    }
}