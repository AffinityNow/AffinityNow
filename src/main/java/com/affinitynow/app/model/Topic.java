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
    @OneToMany(targetEntity=RatedTopic.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "topic")
    private Set<RatedTopic> ratedTopics;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Topic other = (Topic) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public Topic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
