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
    public String convertToClientFormat(String format) {
        StringBuilder stringBuilderSS = new StringBuilder(format);
        String SS = String.valueOf(stringBuilderSS.delete(0, 7));

        StringBuilder stringBuilderNNNNN = new StringBuilder(format);
        String NNNNNN = String.valueOf(stringBuilderNNNNN.delete(6, 9));
        return NNNNNN + " " + SS;
    }
}
