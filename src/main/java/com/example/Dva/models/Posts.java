package com.example.Dva.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER)
    private Collection<Data> tenants;

    public Collection<Data> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Data> tenants) {
        this.tenants = tenants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
