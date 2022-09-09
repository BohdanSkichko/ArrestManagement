package com.example.arrestmanagement.dto;

import com.example.arrestmanagement.validation.handling.constraint.DateConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.*;
import java.sql.Date;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor

public class ArrestDTO {

    @DateConstraint
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty("DocDate")
    private Date docDate;


    @Size(max = 30, message = "max 30 symbols")
    @Pattern(regexp = "[\\w\\d\\s-#№]+", message = "You can only use: A-Z a-z 0-9 # № -")
    @JsonProperty("DocNum")
    private String docNum;

    @Size(max = 1000, message = "max 1000 symbols")
    @JsonProperty("Purpose")
    private String purpose;


    @Min(value = 0, message = "the amount must be only positive")
    @JsonProperty("Amount")
    private Long amount;

    @Pattern(regexp = "[\\w\\d\\s-#№]+", message = "You can only use: A-Z a-z 0-9 # № -")
    @JsonProperty("RefDocNum")
    private String refDocNum;


    @Min(value = 1, message = "You can only use operation 1, 2 or 3")
    @Max(value = 3, message = "You can only use operation 1, 2 or 3")
    @JsonProperty("Operation")
    private int operation;
}
