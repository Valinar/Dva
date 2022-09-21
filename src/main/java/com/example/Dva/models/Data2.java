package com.example.Dva.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table (name = "Book")
public class Data2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле не модет быть пустым")
    @Size(min = 2,max = 50,message = "Нужно в диапазоне от 2 до 50")
    private String name,avtor;
    @PositiveOrZero(message = "Поле не модет быть пустым")
    private int year,count;
    @PositiveOrZero(message = "Поле не модет быть пустым")
    private double price;




    public Data2(){}
    public Data2(String name, String avtor, int year, int count, double price) {
        this.name = name;
        this.avtor = avtor;
        this.year = year;
        this.count = count;
        this.price = price;
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

    public String getAvtor() {
        return avtor;
    }

    public void setAvtor(String avtor) {
        this.avtor = avtor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
