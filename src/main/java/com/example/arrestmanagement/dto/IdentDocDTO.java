package com.example.arrestmanagement.dto;

import com.example.arrestmanagement.validation.handling.constraint.DateConstraint;
import com.example.arrestmanagement.validation.handling.constraint.IdentDocTypeConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode

@AllArgsConstructor
@RequiredArgsConstructor
public class IdentDocDTO {

    @IdentDocTypeConstraint
    @JsonProperty("Type")
    private int type;


    @JsonProperty("NumberSeries")
    private String numberSeries;

    @DateConstraint
    @JsonProperty("IssueDate")
    private Date issueDate;




}
