package com.affinitynow.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.affinitynow.app.utilisateur.dto.UtilisateurDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import org.springframework.lang.Nullable;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer score;
    @ManyToOne
    @JsonBackReference
    @Nullable
    private Utilisateur utilisateur;


    public String getName() {
        return name;
    }

    public Topic setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public Topic setScore(Integer score) {
        this.score = score;
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


}
