package com.affinitynow.app.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.Map;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(unique=true, nullable = false)
    private String pseudo;
    @ElementCollection
    private Map<String, Knowledge> knowledge = Collections.emptyMap();

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPseudo() {
        return pseudo;
    }

    public User setPseudo(String pseudo) {
        this.pseudo = pseudo;
        return this;
    }


	public Map<String, Knowledge> getKnowledge() {
		return knowledge;
	}

	public void setConnaissances(Map<String, Knowledge> knowledges) {
		this.knowledge = knowledges;
	}

    public User(String pseudo, Map<String, Knowledge> knowledges) {
        this.pseudo = pseudo;
        this.knowledge = knowledges;
    }

    public User() {
    }
}
