package com.example.arrestmanagement.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Target;
import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity

@Table(name = "dul")
public class IdentDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Min(1)
    @Max(2)
    @Column(name = "type")
    private int type;

//
//    @Pattern(regexp = "[A-Z]{6} \\d{2} \\d{2}", message = "Please use pattern NNNNNN SS SS, where N - Letter, S - Digit")
//    @Column(name = "passport")
//    private String passport;
//
//
//    @Pattern(regexp = "[A-Z]{6} \\d{2}", message = "Please use pattern NNNNNN SS, where N - Letter, S - Digit")
//    @Column(name = "foreign_passport")
//    private String foreignPassport;


    @Column (name = "issue_date")
    private Date issueDate;


    public IdentDoc() {
    }
}
