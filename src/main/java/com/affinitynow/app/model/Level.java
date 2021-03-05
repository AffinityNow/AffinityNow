package com.affinitynow.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Level {

    ONE("ONE"), TWO("TWO"), THREE("THREE"), FOUR("FOUR"), FIVE("FIVE");

    private final String levelAttribute;

    public int value() {
        return switch (this) {
            case ONE ->  1;
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
        };
    }

    private Level(String level) {
        this.levelAttribute = level;
    }

    public String getLevel() {
        return levelAttribute;
    }

}
