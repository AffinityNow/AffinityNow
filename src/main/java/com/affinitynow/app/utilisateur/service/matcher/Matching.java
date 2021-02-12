package com.affinitynow.app.utilisateur.service.matcher;

import com.affinitynow.app.model.Utilisateur;
import org.springframework.stereotype.Component;
@Component
public interface Matching {
    MatchResult matching(Utilisateur utilisateur, Utilisateur utilisateur1);
}
