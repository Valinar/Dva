package com.example.Dva.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Awards {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
    @ManyToMany
    @JoinTable(name="emp_awards",
            joinColumns=@JoinColumn(name="awards_id"),
            inverseJoinColumns=@JoinColumn(name="data_id"))
    private List<Data> dataList;
@OneToOne(optional = true, cascade = CascadeType.ALL)
   @JoinColumn(name = "award_id")
private Awardlicens lic;
    public String getName() {
        return name;
    }
public Awards(){}
    public Awards(Awardlicens lic, String name) {
        this.lic = lic;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    @Column
    private String name;

}
