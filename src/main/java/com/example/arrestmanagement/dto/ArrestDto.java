package com.example.arrestmanagement.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor

public class ArrestDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty("DocDate")
    private Date docDate;

    @Size(max = 30, message = "This field supports a maximum of 30 characters")
    @Pattern(regexp = "[\\w\\d\\s-#№]+", message = "You can only use: A-Z a-z 0-9 # № -")
    @JsonProperty("DocNum")
    private String docNum;

    @Size(max = 1000, message = "This field supports a maximum of 10000 characters")
    @JsonProperty("Purpose")
    private String purpose;


    @Min(value = 0, message = "The amount must be positive")
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
