package com.affinitynow.app.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

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
    @ElementCollection
    private Set<User> friends;

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

    public User(String pseudo, Map<String, Knowledge> likedKnowledges, Map<String, Knowledge> seekedKnowledges) {
        this.pseudo = pseudo;
        this.likedKnowledges = likedKnowledges;
        this.seekedKnowledges = seekedKnowledges;
    }

    public User() {
    }

    public Map<String, Knowledge> getSeekedKnowledges() {
        return seekedKnowledges;
    }

    public void setSeekedKnowledges(Map<String, Knowledge> seekedKnowledges) {
        this.seekedKnowledges = seekedKnowledges;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
