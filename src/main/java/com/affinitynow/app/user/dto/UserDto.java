package com.affinitynow.app.user.dto;

import com.affinitynow.app.model.Knowledge;

import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.Map;


public class UserDto {
    @Nullable
    private Long id;
    private String pseudo;
    private Map<String, Knowledge> knowledge = Collections.emptyMap();

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

    public Map<String, Knowledge> getConnaissances() {
        return knowledge;
    }

    public UserDto setConnaissances(Map<String, Knowledge> knowledge) {
        this.knowledge = knowledge;
        return this;
    }

    public void addConnaissance(String name, Knowledge knowledge) {
        this.knowledge.put(name, knowledge);
    }
}
