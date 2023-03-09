package com.example.arrestmanagement.dto;


import com.example.arrestmanagement.validation.handling.constraint.IdentDocTypeConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


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


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty("IssueDate")
    private Date issueDate;


}
