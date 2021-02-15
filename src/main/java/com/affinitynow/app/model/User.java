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
    private Map<String, Knowledge> likedKnowledges = Collections.emptyMap();
    @ElementCollection
    private Map<String, Knowledge> seekedKnowledges = Collections.emptyMap();

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

	public Map<String, Knowledge> getLikedKnowledges() {
		return likedKnowledges;
	}

	public void setLikedKnowledges(Map<String, Knowledge> knowledges) {
		this.likedKnowledges = knowledges;
	}

    public User(String pseudo, Map<String, Knowledge> knowledges) {
        this.pseudo = pseudo;
        this.likedKnowledges = knowledges;
    }

    public User() {
    }

    public Map<String, Knowledge> getSeekedKnowledges() {
        return seekedKnowledges;
    }

    public void setSeekedKnowledges(Map<String, Knowledge> seekedKnowledges) {
        this.seekedKnowledges = seekedKnowledges;
    }
}
