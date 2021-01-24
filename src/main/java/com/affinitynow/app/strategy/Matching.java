package com.affinitynow.app.strategy;

import com.affinitynow.app.model.Utilisateur;
import org.springframework.stereotype.Component;
@Component
public interface Matching {
    Boolean matching(Utilisateur utilisateur, Utilisateur utilisateur1);
}
