package com.affinitynow.app.utilisateur.dto;

import com.affinitynow.app.model.Topic;
import com.sun.istack.Nullable;

public class TopicDto {
    @Nullable
    private Long id;
    private String name;
    private Integer score;

    public String getName() {
        return name;
    }

    public TopicDto setName(String name) {
        this.name = name;
        return this;
    }

    public TopicDto setScore(Integer score) {
        this.score = score;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public Long getId() {
        return id;
    }

    public TopicDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Topic toTopic() {
        return new Topic().setId(id).setName(name).setScore(score);
    }

    public static TopicDto from(Topic src) {
        return new TopicDto().setId(src.getId()).setScore(src.getScore()).setName(src.getName());
    }

}
