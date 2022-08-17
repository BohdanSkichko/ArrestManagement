package com.example.arrestmanagement.dto;

import lombok.*;

import javax.persistence.Column;
import java.sql.Date;

@Getter
@Setter
@ToString

@AllArgsConstructor
@RequiredArgsConstructor
public class IdentDoc {

    @Column(name = "type")
    private int type;


    @Column(name = "number_series")
    private String numberSeries;


    @Column(name = "issue_date")
    private Date issueDate;

}
