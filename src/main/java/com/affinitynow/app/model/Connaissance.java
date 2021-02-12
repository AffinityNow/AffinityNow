package com.affinitynow.app.model;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Embeddable
public class Connaissance {
    @Embedded
    private Topic topic;
    @Convert(converter = NiveauConverter.class, attributeName = "niveau")
    private Niveau niveau;

    public Topic topic() {
        return topic;
    }

    public Niveau niveau() {
        return niveau;
    }

    public Connaissance(Topic topic, String niveau) {
        this.topic = topic;
        this.niveau = Niveau.valueOf(niveau);
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

    public Connaissance() {
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
}
