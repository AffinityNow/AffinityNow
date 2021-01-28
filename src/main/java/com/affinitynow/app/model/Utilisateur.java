package com.affinitynow.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String pseudo;
    @OneToMany(targetEntity = Topic.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Nullable
    @JsonManagedReference
    private Set<Topic> topics;
    @ElementCollection
    // @CollectionTable(name= "utilisateur_connaissances", joinColumns = @JoinColumn(name = "connaisanace_id"), foreignKey = @ForeignKey(name = "utilisateur_connaissances_fk"))
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

    @Nullable
    public Set<Topic> getTopics() {
        return topics;
    }

    public Utilisateur setTopics(@Nullable Set<Topic> topics) {
        this.topics = topics;
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
