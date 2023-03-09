package com.example.arrestmanagement.parameter;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OuterIdentDoc {
    private int type;
    private int organCode;
    private String numSeries;
    public OuterIdentDoc() {
    }
}
