package com.affinitynow.app.utilisateur.dto;

import com.affinitynow.app.model.Connaissance;
import com.sun.istack.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Set;


public class UtilisateurDto {
    @Nullable
    private Long id;
    private String pseudo;
    // private Set<RatedTopicDto> topics;
    private Map<String, Connaissance> connaissances = Collections.emptyMap();

    public Long getId() {
        return id;
    }

    public UtilisateurDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPseudo() {
        return pseudo;
    }

    public UtilisateurDto setPseudo(String pseudo) {
        this.pseudo = pseudo;
        return this;
    }

    // public Set<RatedTopicDto> getTopics() {
    //     return topics;
    // }

    // public UtilisateurDto setTopics(Set<RatedTopicDto> topics) {
    //     this.topics = topics;
    //     return this;
    // }

    public Map<String, Connaissance> getConnaissances() {
        return connaissances;
    }

    public UtilisateurDto setConnaissances(Map<String, Connaissance> connaissances) {
        this.connaissances = connaissances;
        return this;
    }

    public void addConnaissance(String name, Connaissance connaissance) {
        this.connaissances.put(name, connaissance);
    }
}
