package com.affinitynow.app.topic.dto;

import com.affinitynow.app.model.Topic;

public class TopicDto {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public TopicDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TopicDto setName(String name) {
        this.name = name;
        return this;
    }

    public static TopicDto from(Topic src) {
        return new TopicDto().setId(src.getId()).setName(src.getName());
    }
}
