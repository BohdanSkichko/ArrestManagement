package com.example.arrestmanagement.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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



    @Column(name = "doc_date")
    private Date docDate;

    @Pattern(regexp = "[A-Za-z0-9#№-]", message = "You can use only: A-Z a-z 0-9 # № -")
    @Column(name = "dacNum", length = 30)
    private String docNum;

    @Column(name = "purpose", length = 1000)
    private String purpose;

    @Column(name = "amount")
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTING;


    @Enumerated(EnumType.STRING)
    @Column(name = "organ_code")
    private OrganCode organCode;

    enum OrganCode {
        FSSP(17),
        FNS(39);

        private final int code;

        public int getCode() {
            return code;
        }

        OrganCode(int code) {
            this.code = code;
        }

    }




    @ManyToOne()
    @JoinColumn(name = "client_id")
    private Client client;

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
