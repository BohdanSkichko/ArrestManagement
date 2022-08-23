package com.example.arrestmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString

@AllArgsConstructor
@RequiredArgsConstructor
public class ArrestRequest {
    @JsonProperty("RequestId")
    private String requestID;

    @JsonProperty("LastName")
    private String lastname;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("IdentDoc")
    private IdentDocDTO identDocDTO;


//    @Pattern(regexp = "^17|^39", message = "You can only use:  17 or 39")
    @JsonProperty("OrganCode")
    private int organCode;

    @Valid
    @JsonProperty("Arrest")
    private ArrestDTO arrestDTO;
}
