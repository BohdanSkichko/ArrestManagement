package com.example.arrestmanagement.dto;

import com.example.arrestmanagement.validation.handling.constraint.OrganCodeConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@EqualsAndHashCode


@AllArgsConstructor
@RequiredArgsConstructor
public class ArrestRequest {
    @JsonProperty("RequestId")
    private String requestID;

    @Size(max = 100, message = "This field supports a maximum of 100 characters")
    @JsonProperty("LastName")
    private String lastname;

    @Size(max = 100, message = "This field supports a maximum of 100 characters")
    @JsonProperty("FirstName")
    private String firstName;

    @Valid
    @JsonProperty("IdentDoc")
    private IdentDocDto identDocDTO;

    @OrganCodeConstraint
    @JsonProperty("OrganCode")
    private int organCode;

    @Valid
    @JsonProperty("Arrest")
    private ArrestDto arrestDTO;
}
