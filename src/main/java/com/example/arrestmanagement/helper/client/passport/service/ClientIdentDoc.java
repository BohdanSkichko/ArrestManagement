package com.example.arrestmanagement.helper.client.passport.service;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Component
public class ClientIdentDoc {
    private String numSeries;
    private int dulType;
    public ClientIdentDoc() {
    }
}
