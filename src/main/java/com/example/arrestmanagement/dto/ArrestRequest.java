package com.example.arrestmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@ToString

@AllArgsConstructor
@RequiredArgsConstructor
public class ArrestRequest {
    @JsonProperty("RequestId") //GUID
    private String requestID;

    @JsonProperty("LastName")
    private String lastname;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("IdentDoc")
    private IdentDoc identDoc;

    @JsonProperty("OrganCode")
    private int organCode;
}
