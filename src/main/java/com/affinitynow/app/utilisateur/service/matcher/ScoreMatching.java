package com.affinitynow.app.utilisateur.service.matcher;

import java.util.Set;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import com.affinitynow.app.model.Connaissance;
import com.affinitynow.app.model.Niveau;
import com.affinitynow.app.model.Utilisateur;
import com.affinitynow.app.utilisateur.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("score")
public class ScoreMatching implements Matching {

    @Autowired
    private UtilisateurService userService;
    IntPredicate isHigherThan3 = x -> x >= 3;

    @Override
    public MatchResult matching(Utilisateur utilisateur, Utilisateur utilisateur1) {
        Set<Connaissance> intersection = userService.connaissance(utilisateur)
            .filter(c -> userService.connait(c.topic(), utilisateur1))
            .filter(p -> userService.niveau(utilisateur, p.topic()).map(Niveau::value).filter(isHigherThan3::test).isPresent()
                && userService.niveau(utilisateur1, p.topic()).map(Niveau::value).filter(isHigherThan3::test).isPresent())
            .collect(Collectors.toSet());

        return MatchHelper.checkFunctionBooleanMatchResult.apply(intersection);
    }

}
