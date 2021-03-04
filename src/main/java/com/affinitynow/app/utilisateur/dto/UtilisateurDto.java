package com.affinitynow.app.utilisateur.dto;

import com.affinitynow.app.model.Connaissance;

import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.Map;


public class UtilisateurDto {
    @Nullable
    private Long id;
    private String pseudo;
    private Map<String, Connaissance> connaissances = Collections.emptyMap();
    private Map<String, Connaissance> seeked = Collections.emptyMap();



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

    public Map<String, Connaissance> getConnaissances() {
        return connaissances;
    }
    public Map<String, Connaissance> getSeeked() {
        return seeked;
    }

    public UtilisateurDto setConnaissances(Map<String, Connaissance> connaissances) {
        this.connaissances = connaissances;
        return this;
    }

    public UtilisateurDto setSeeked(Map<String, Connaissance> seeked) {
        this.seeked = seeked;
        return this;
    }

    public void addConnaissance(String name, Connaissance connaissance) {
        this.connaissances.put(name, connaissance);
    }

    public void addSeeked(String name, Connaissance seek) {
        this.seeked.put(name, seek);
    }
}
