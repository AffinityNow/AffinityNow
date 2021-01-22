package com.affinitynow.app.model;

import java.util.HashMap;
import java.util.Map;

public class Utilisateur {
    private String pseudo;
    private Map<String, Topic> topics;
    private Matching matching;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Map<String, Topic> getTopics() {
        return topics;
    }

    public void setTopics(Map<String, Topic> topics) {
        this.topics = topics;
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
        this.topics = new HashMap<>();
    }
}
