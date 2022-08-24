package com.example.arrestmanagement.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100)
    private String firstName;

    @Column(name = "surname", length = 100)
    private String lastName;


    @Column(name = "dul_type")
    private int dulType;

    @Column(name = "numSeries", length = 12)
    private String numSeries;


    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "place_birth", length = 250)
    private String placeOfBirth;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST}, mappedBy = "client")
    private List<Arrest> arrests;


    public Client() {
    }

    public void addArrestToClient(Arrest arrest) {
        if (arrests == null) {
            arrests = new ArrayList<>();
        }
        arrests.add(arrest);
        arrest.setClient(this);
    }
}



