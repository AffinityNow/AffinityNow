package com.affinitynow.app.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.Map;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(unique=true)
    private String pseudo;
    @ElementCollection
    private Map<String, Connaissance> connaissances = Collections.emptyMap();

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

	public void setConnaissances(Map<String, Connaissance> connaissances) {
		this.connaissances = connaissances;
	}

    public Utilisateur(String pseudo, Map<String, Connaissance> connaissances) {
        this.pseudo = pseudo;
        this.connaissances = connaissances;
    }

    public Utilisateur() {
    }
}
