package com.example.arrestmanagement.helper.client.passport.service;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ArrestDocType {
    private int type;
    private int organCode;
    private String numSeries;
    public ArrestDocType() {
    }
}
