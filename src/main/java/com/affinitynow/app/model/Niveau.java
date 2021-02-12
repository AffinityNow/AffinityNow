package com.affinitynow.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Niveau {

    UN("UN"), DEUX("DEUX"), TROIS("TROIS"), QUATRE("QUATRE"), CINQ("CINQ");

    private final String niveau;

    public int value() {
        return switch (this) {
            case UN ->  1;
            case DEUX -> 2;
            case TROIS -> 3;
            case QUATRE -> 4;
            case CINQ -> 5;
        };
    }

    private Niveau(String niveau) {
        this.niveau = niveau;
    }

    public String getNiveau() {
        return niveau;
    }

}
