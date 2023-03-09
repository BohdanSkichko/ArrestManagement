package com.example.arrestmanagement.parameter;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class InnerIdentDoc {
    private String numSeries;
    private int dulType;
    public InnerIdentDoc() {
    }
}
