package com.affinitynow.app.utilisateur.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.affinitynow.app.model.Matching;
import com.affinitynow.app.model.Topic;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.lang.Nullable;

public class UtilisateurDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pseudo;
    @OneToMany(targetEntity=Topic.class, mappedBy="utilisateur", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Nullable
    @JsonManagedReference
    private Set<Topic> topics;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Utilisateur [matching=");
        builder.append(", pseudo=");
        builder.append(pseudo);
        builder.append(", topics=");
        builder.append(topics);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
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
        UtilisateurDto other = (UtilisateurDto) obj;
        if (pseudo == null) {
            if (other.pseudo != null)
                return false;
        } else if (!pseudo.equals(other.pseudo))
            return false;
        return true;
    }

    public UtilisateurDto(String pseudo, Matching matching) {
        this.pseudo = pseudo;
        this.topics = new HashSet<>();
    }
}
