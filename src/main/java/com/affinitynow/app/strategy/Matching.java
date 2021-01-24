package com.affinitynow.app.strategy;

import com.affinitynow.app.model.Utilisateur;
public interface Matching {
    Boolean matching(Utilisateur utilisateur, Utilisateur utilisateur1);
}
