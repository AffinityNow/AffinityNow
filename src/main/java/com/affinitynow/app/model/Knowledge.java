package com.affinitynow.app.model;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Embeddable
public class Knowledge {
    @Embedded
    private Topic topic;
    @Convert(converter = LevelConverter.class, attributeName = "level")
    private Level level;

    public Topic topic() {
        return topic;
    }

    public Level level() {
        return level;
    }

    public Knowledge(Topic topic, String level) {
        this.topic = topic;
        this.level = Level.valueOf(level);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Knowledge [level=");
        builder.append(level);
        builder.append(", topic=");
        builder.append(topic);
        builder.append("]");
        return builder.toString();
    }

    public Knowledge() {
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level niveau) {
        this.level = niveau;
    }
}
