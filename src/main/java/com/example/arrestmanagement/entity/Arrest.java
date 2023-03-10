package com.example.arrestmanagement.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;



@Getter
@Setter
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "arrests")
public class Arrest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "doc_date")
    private Date docDate;


    @Column(name = "docNum", length = 30)
    private String docNum;

    @Column(name = "purpose", length = 1000)
    private String purpose;


    @Column(name = "amount")
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTING;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "client_id")
    private Client client;


    @Column(name = "organ_code")
    private int organCode;


    public Arrest() {
    }

    public enum Status {
        ACTING("Acting"),
        COMPLETED("Completed"),
        CANCELED("Canceled");

        private final String status;

        public String getStatus() {
            return status;
        }

        Status(String status) {
            this.status = status;
        }
    }
}

