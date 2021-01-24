package com.affinitynow.app.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.affinitynow.app.utilisateur.dto.UtilisateurDto;

@Entity
public class RatedTopic {
    @EmbeddedId
    RatedTopicKey id;

    @ManyToOne
    @MapsId("topicId")
    @JoinColumn(name = "topic_id")
    Topic topic;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    UtilisateurDto user;

    int rating;

    public RatedTopicKey getId() {
        return id;
    }

    public void setId(RatedTopicKey id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public UtilisateurDto getUser() {
        return user;
    }

    public void setUser(UtilisateurDto user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        RatedTopic other = (RatedTopic) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public RatedTopic(RatedTopicKey id, Topic topic, UtilisateurDto user, int rating) {
        this.id = id;
        this.topic = topic;
        this.user = user;
        this.rating = rating;
    }
}
