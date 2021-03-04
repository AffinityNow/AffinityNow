package com.affinitynow.app.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.Map;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(unique=true, nullable = false)
    private String pseudo;
    @ElementCollection
    private Map<String, Connaissance> connaissances = Collections.emptyMap();
    @ElementCollection
    private Map<String, Connaissance> seeked = Collections.emptyMap();

    public Long getId() {
        return id;
    }

    public Utilisateur setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Utilisateur setPseudo(String pseudo) {
        this.pseudo = pseudo;
        return this;
    }


	public Map<String, Connaissance> getConnaissances() {
		return connaissances;
	}

    public Map<String, Connaissance> getSeeked() {
        return seeked;
    }

	public void setConnaissances(Map<String, Connaissance> connaissances) {
		this.connaissances = connaissances;
	}

    public void setSeeked(Map<String, Connaissance> seeked) {
        this.seeked = seeked;
    }

    public Utilisateur(String pseudo, Map<String, Connaissance> connaissances, Map<String, Connaissance> seeked) {
        this.pseudo = pseudo;
        this.connaissances = connaissances;
        this.seeked = seeked;
    }

    public Utilisateur() {
    }
}
