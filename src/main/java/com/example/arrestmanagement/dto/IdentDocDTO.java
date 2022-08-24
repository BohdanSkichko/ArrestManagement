package com.example.arrestmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode

@AllArgsConstructor
@RequiredArgsConstructor
public class IdentDocDTO {

    @JsonProperty("Type")
    private int type;


    @JsonProperty("NumberSeries")
    private String numberSeries;


    @JsonProperty("IssueDate")
    private Date issueDate;

}
