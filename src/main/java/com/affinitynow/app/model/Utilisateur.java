package com.affinitynow.app.model;

import java.util.HashSet;
import java.util.Set;

public class Utilisateur {
    private String pseudo;
    private Matching matching;
    private Set<RatedTopic> ratedTopics;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Matching getMatching() {
        return matching;
    }

    public void setMatching(Matching matching) {
        this.matching = matching;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Utilisateur [matching=");
        builder.append(matching);
        builder.append(", pseudo=");
        builder.append(pseudo);
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
        Utilisateur other = (Utilisateur) obj;
        if (pseudo == null) {
            if (other.pseudo != null)
                return false;
        } else if (!pseudo.equals(other.pseudo))
            return false;
        return true;
    }

    public Utilisateur(String pseudo, Matching matching) {
        this.pseudo = pseudo;
        this.matching = matching;
        this.ratedTopics = new HashSet<>();
    }

    public Set<RatedTopic> getRatedTopics() {
        return ratedTopics;
    }

    public void setRatedTopics(Set<RatedTopic> ratedTopics) {
        this.ratedTopics = ratedTopics;
    }
}
