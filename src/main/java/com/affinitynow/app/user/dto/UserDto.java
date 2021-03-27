package com.affinitynow.app.user.dto;

import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Set;


public class UserDto {
    @Nullable
    private Long id;
    private String pseudo;
    private Map<String, Knowledge> seekedKnowledges = Collections.emptyMap();
    private Map<String, Knowledge> likedKnowledges = Collections.emptyMap();
    @JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPseudo() {
        return pseudo;
    }

    public UserDto setPseudo(String pseudo) {
        this.pseudo = pseudo;
        return this;
    }

    public Map<String, Knowledge> getSeekedKnowledges() {
        return seekedKnowledges;
    }

    public UserDto setSeekedKnowledges(Map<String, Knowledge> knowledge) {
        this.seekedKnowledges = knowledge;
        return this;
    }

    public Map<String, Knowledge> getLikedKnowledges() {
        return likedKnowledges;
    }

    public UserDto setLikedKnowledges(Map<String, Knowledge> likedKnowledges) {
        this.likedKnowledges = likedKnowledges;
        return this;
    }

    public UserDto() {
    }

    public static UserDto fromEntity(User user) {
        return new UserDto().setPseudo(user.getPseudo()).setId(user.getId()).setLikedKnowledges(user.getLikedKnowledges()).setSeekedKnowledges(user.getSeekedKnowledges());
    }
}
