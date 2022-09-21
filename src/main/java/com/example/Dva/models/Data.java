package com.example.Dva.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Emp")
public class Data {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotEmpty(message = "Поле не модет быть пустым")
@Size(min = 2,max = 50,message = "Нужно в диапазоне от 2 до 50")
    private String name,fame, otch;;
    @PositiveOrZero(message = "Поле не модет быть пустым")
    private int place;
    @PositiveOrZero(message = "Поле не модет быть пустым")
    private double zp;
    @ManyToMany
    @JoinTable(name="emp_awards",
            joinColumns=@JoinColumn(name="data_id"),
            inverseJoinColumns=@JoinColumn(name="awards_id"))
    private List<Awards> awardsList;
    public List<Awards> getAwardsList() {
        return awardsList;
    }

    public void setAwardsList(List<Awards> awardsList) {
        this.awardsList = awardsList;
    }



@ManyToOne(optional = true, cascade = CascadeType.ALL)
private Posts posts;

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFame() {
        return fame;
    }

    public void setFame(String fame) {
        this.fame = fame;
    }

    public String getOtch() {
        return otch;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public double getZp() {
        return zp;
    }

    public void setZp(double zp) {
        this.zp = zp;
    }
    public Data(){}

    /*public Data(String name, String fame, String otch, int place, double zp, Posts post) {
        this.name = name;
        this.fame = fame;
        this.otch = otch;
        this.place = place;
        this.zp = zp;
        this.post = post;
    }*/
}
