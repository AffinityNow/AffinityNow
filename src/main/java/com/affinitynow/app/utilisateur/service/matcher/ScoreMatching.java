package com.affinitynow.app.utilisateur.service.matcher;

import java.util.stream.Collectors;

import com.affinitynow.app.model.Connaissance;
import com.affinitynow.app.model.Utilisateur;
import com.affinitynow.app.utilisateur.service.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("score")
public class ScoreMatching implements Matching {

    @Autowired
    private UtilisateurService userService;

    @Override
    public MatchResult matching(Utilisateur utilisateur, Utilisateur utilisateur1) {
        MatchResult rtr = new ClassicMatchResult();

        userService.connaissance(utilisateur)
            .filter(c -> userService.connait(c.topic(), utilisateur1))
            .filter(p -> userService.niveau(utilisateur, p.topic()).get().value() >= 3
                && userService.niveau(utilisateur1, p.topic()).get().value() >= 3)
            .collect(Collectors.toSet());
        rtr.setSuccess()
    }
}
