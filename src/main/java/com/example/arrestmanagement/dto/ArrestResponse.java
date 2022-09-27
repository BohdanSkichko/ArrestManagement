package com.example.arrestmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode

@AllArgsConstructor
@RequiredArgsConstructor


public class ArrestResponse {
    @JsonProperty("ArrestId")
    private Long id;


    @JsonProperty("ResultCode")
    private int resultCode;

    @JsonProperty("ResultText")
    private String decryption;

}
