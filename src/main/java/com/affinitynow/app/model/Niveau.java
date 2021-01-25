package com.affinitynow.app.model;

import javax.persistence.Embeddable;

@Embeddable
public enum Niveau {
    UN, DEUX, TROIS, QUATRE, CINQ;

    public int value() {
        return switch (this) {
            case UN ->  1;
            case DEUX -> 2;
            case TROIS -> 3;
            case QUATRE -> 4;
            case CINQ -> 5;
        };
    }
}
