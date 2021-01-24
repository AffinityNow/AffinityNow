package com.affinitynow.app.utilisateur.dto;

import com.sun.istack.Nullable;

public class RatedTopicDto {
    @Nullable
    private Long id;
    private Integer score;

    public Long getId() {
        return id;
    }

    public RatedTopicDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public RatedTopicDto setScore(Integer score) {
        this.score = score;
        return this;
    }
}
