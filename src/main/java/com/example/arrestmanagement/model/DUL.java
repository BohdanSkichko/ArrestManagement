package com.example.arrestmanagement.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity

@Table(name = "dul")
public class DUL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ident_doc_id")
    private IdentDoc identDoc;

    public DUL() {
    }
}

//    @Pattern(regexp = "[A-Z]{6} \\d{2} \\d{2}", message = "Please use pattern NNNNNN SS SS, where N - Letter, S - Digit")
//    @Column(name = "passport")
//    private String passport;
//
//
//    @Pattern(regexp = "[A-Z]{6} \\d{2}", message = "Please use pattern NNNNNN SS, where N - Letter, S - Digit")
////    @Column(name = "foreign_passport")
////    private String foreignPassport;
//
//
//


