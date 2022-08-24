package com.example.arrestmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.constraints.*;
import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode

@AllArgsConstructor
@RequiredArgsConstructor

public class ArrestDTO {

    @JsonProperty("DocDate") //
    private Date docDate;


    @Size(max = 30,message = "max 30 symbols")
    @Pattern(regexp = "[\\w\\d\\s-#№]+", message = "You can use only: A-Z a-z 0-9 # № -")
    @JsonProperty("DocNum")
    private String docNum;

    @Size(max = 1000)
    @JsonProperty("Purpose")
    private String purpose;


    @JsonProperty("Amount")
    private Long amount;


    @JsonProperty("RefDocNum")
    private String refDocNum;


    @Min(value = 1, message = "You can only use operation 1, 2 or 3")
    @Max(value = 3, message = "You can only use operation 1, 2 or 3")
    @JsonProperty("Operation")
    private int operation;
}
