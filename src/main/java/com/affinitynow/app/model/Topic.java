package com.affinitynow.app.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public Topic setName(String name) {
        this.name = name;
        return this;
    }

    public Topic() {
    }

    public Long getId() {
        return id;
    }

    public Topic setId(Long id) {
        this.id = id;
        return this;
    }


    public Topic(String name) {
        this.name = name;
    }
}
