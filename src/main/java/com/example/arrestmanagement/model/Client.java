package com.example.arrestmanagement.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 100)
    private String firstName;

    @Column(name = "surname", length = 100)
    private String lastName;


    @OneToOne
    @JoinColumn(name = "dul_id") // type DUL  1 or 2
    private DUL dul;


//    @Enumerated(EnumType.STRING)
//    @Column(name = "dul")
//    private Dul dul;
//
//
//
//    enum Dul {
//        PASSPORT(1),
//        FOREIGN_PASSPORT(2)
//        ;
//
//        private final int type;
//
//        Dul(int type) {
//            this.type = type;
//        }
//    }


    @Column(name = "birthday")
    private Date birthday; //yyyy-MM-dd

    @Column(name = "place_birth", length = 250)
    private String placeOfBirth;


    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "client")
    private List<Arrest> arrests;


    public Client() {
    }
}
