package com.affinitynow.app.model;

import com.affinitynow.app.api.BiDirectionNetwork;
import com.affinitynow.app.api.Network;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    @Email
    @NotNull
    private String email ;
    @Embedded
    private Network uniDirectionNetwork;
    @Embedded
    private BiDirectionNetwork biDirectionNetwork;

    public User(String pseudo, Map<String, Knowledge> likedKnowledges, Map<String, Knowledge> seekedKnowledges, Set<User> friends, @Email @NotNull String email) {
        this.pseudo = pseudo;
        this.likedKnowledges = likedKnowledges;
        this.seekedKnowledges = seekedKnowledges;
        this.friends = friends;
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

    public Network getUniDirectionNetwork() {
        return uniDirectionNetwork;
    }

    public void setUniDirectionNetwork(Network uniDirectionNetwork) {
        this.uniDirectionNetwork = uniDirectionNetwork;
    }

    public BiDirectionNetwork getBiDirectionNetwork() {
        return biDirectionNetwork;
    }

    public void setBiDirectionNetwork(BiDirectionNetwork biDirectionNetwork) {
        this.biDirectionNetwork = biDirectionNetwork;
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
