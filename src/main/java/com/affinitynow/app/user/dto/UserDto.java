package com.affinitynow.app.user.dto;

import com.affinitynow.app.model.Knowledge;

import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.Map;


public class UserDto {
    @Nullable
    private Long id;
    private String pseudo;
    private Map<String, Knowledge> seekedKnowledges = Collections.emptyMap();
    private Map<String, Knowledge> likedKnowledges = Collections.emptyMap();

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

    public void addSeekedKnowledges(String name, Knowledge knowledge) {
        this.seekedKnowledges.put(name, knowledge);
    }

    public Map<String, Knowledge> getLikedKnowledges() {
        return likedKnowledges;
    }

    public void setLikedKnowledges(Map<String, Knowledge> likedKnowledges) {
        this.likedKnowledges = likedKnowledges;
    }
}
