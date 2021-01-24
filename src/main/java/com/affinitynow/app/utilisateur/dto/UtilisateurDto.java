package com.affinitynow.app.utilisateur.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.affinitynow.app.model.RatedTopic;


@Entity
public class UtilisateurDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pseudo;
    @OneToMany(targetEntity=RatedTopic.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<RatedTopic> ratedTopics;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UtilisateurDto [id=");
        builder.append(id);
        builder.append(", pseudo=");
        builder.append(pseudo);
        builder.append(", ratedTopics=");
        builder.append(ratedTopics);
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

    public UtilisateurDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<RatedTopic> getRatedTopics() {
        return ratedTopics;
    }

    public void setRatedTopics(Set<RatedTopic> ratedTopics) {
        this.ratedTopics = ratedTopics;
    }
}
