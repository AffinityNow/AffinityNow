package com.affinitynow.app.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    @Email
    @NotNull
    private String email ;
    @ElementCollection
    private Set<User> follows;
    @ElementCollection
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private Set<User> friends;

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<User> getFollows() {
        return follows;
    }

    public void setFollows(Set<User> followers) {
        this.follows = followers;
    }


    public User(String pseudo, Map<String, Knowledge> likedKnowledges, Map<String, Knowledge> seekedKnowledges, @Email @NotNull String email) {
        this.pseudo = pseudo;
        this.likedKnowledges = likedKnowledges;
        this.seekedKnowledges = seekedKnowledges;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
}
