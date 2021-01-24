package com.affinitynow.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(targetEntity = RatedTopic.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "topic")
    private Set<RatedTopic> ratedTopics;

    public String getName() {
        return name;
    }

    public Topic setName(String name) {
        this.name = name;
        return this;
    }

    public Topic() {
    }

    public Long getId() {
        return id;
    }

    public Topic setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<RatedTopic> getRatedTopics() {
        return ratedTopics;
    }

    public void setRatedTopics(Set<RatedTopic> ratedTopics) {
        this.ratedTopics = ratedTopics;
    }

    public Topic(String name) {
        this.name = name;
        this.ratedTopics = new HashSet<>();
    }
}
