package com.affinitynow.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class RatedTopicKey implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "topic_id")
    Long topicId;

    @Column(name = "user_id")
    Long userId;

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((topicId == null) ? 0 : topicId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        RatedTopicKey other = (RatedTopicKey) obj;
        if (topicId == null) {
            if (other.topicId != null)
                return false;
        } else if (!topicId.equals(other.topicId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    public RatedTopicKey(Long topicId, Long userId) {
        this.topicId = topicId;
        this.userId = userId;
    }
}
