package com.affinitynow.app.utilisateur.dto;

import com.sun.istack.Nullable;

import java.util.Set;


public class UtilisateurDto {
    @Nullable
    private Long id;
    private String pseudo;
    private Set<RatedTopicDto> topics;

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

    public Set<RatedTopicDto> getTopics() {
        return topics;
    }

    public UtilisateurDto setTopics(Set<RatedTopicDto> topics) {
        this.topics = topics;
        return this;
    }
}
