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
    private Double score;
    @ManyToOne
    @JsonBackReference
    @Nullable
    private UtilisateurDto utilisateurDto;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Topic other = (Topic) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Topic(String name, Double score) {
        this.name = name;
        this.score = score;
    }

    public Topic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UtilisateurDto getUtilisateurDto() {
        return utilisateurDto;
    }

    public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
        this.utilisateurDto = utilisateurDto;
    }
}
