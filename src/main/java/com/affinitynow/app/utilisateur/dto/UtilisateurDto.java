package com.affinitynow.app.utilisateur.dto;

import com.affinitynow.app.model.Utilisateur;
import com.sun.istack.Nullable;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class UtilisateurDto {
    @Nullable
    private Long id;
    private String pseudo;
    private Set<TopicDto> topics;

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

    public Set<TopicDto> getTopics() {
        return topics;
    }

    public UtilisateurDto setTopics(Set<TopicDto> topics) {
        this.topics = topics;
        return this;
    }

    public Utilisateur toUtilisateur() {
        return new Utilisateur().setId(id).setPseudo(pseudo)
                .setTopics(topics.stream().map(TopicDto::toTopic).collect(toSet()));
    }

    public static UtilisateurDto from(Utilisateur src) {
        return new UtilisateurDto().setId(src.getId()).setPseudo(src.getPseudo())
                .setTopics(src.getTopics().stream().map(TopicDto::from).collect(toSet()));
    }
}
