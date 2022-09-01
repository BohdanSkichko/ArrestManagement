package com.example.arrestmanagement.validation.handling.fssp;

import com.example.arrestmanagement.exception.handling.ArrestIncorrectException;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Component
public class FSSPForeignPassport {

    private static final String PATTERN = "[A-Z]{6}\\.\\d{2}";

    private String format;

    public String convertToClientFormat(String format) {
        this.format = format;
        if (this.check()) {
            StringBuilder stringBuilderSS = new StringBuilder(format);
            String SS = String.valueOf(stringBuilderSS.delete(0, 7));

            StringBuilder stringBuilderNNNNN = new StringBuilder(format);
            String NNNNNN = String.valueOf(stringBuilderNNNNN.delete(6, 9));
            return NNNNNN + " " + SS;
        } else throw new ArrestIncorrectException("incorrect NumberSeries");
    }

    private boolean check() {
        return format.matches(PATTERN);
    }
}