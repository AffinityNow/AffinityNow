package com.affinitynow.app.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Connaissance {
    @Embedded
    private Topic topic;
    @Embedded
    private Niveau niveau;

    public Topic topic() {
        return topic;
    }

    public Niveau niveau() {
        return niveau;
    }

    public Connaissance(Topic topic, Niveau niveau) {
        this.topic = topic;
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Connaissance [niveau=");
        builder.append(niveau);
        builder.append(", topic=");
        builder.append(topic);
        builder.append("]");
        return builder.toString();
    }
}
