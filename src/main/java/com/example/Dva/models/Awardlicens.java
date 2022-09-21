package com.example.Dva.models;

import javax.persistence.*;

@Entity
@Table(name = "licencens")
public class Awardlicens {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    public Awards getAwards() {
        return awards;
    }

    public void setAwards(Awards awards) {
        this.awards = awards;
    }

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @OneToOne(optional = true,mappedBy ="lic" )
    private Awards awards;
}
