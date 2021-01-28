package com.affinitynow.app.utilisateur.service.matcher;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.affinitynow.app.model.Connaissance;
import com.affinitynow.app.model.Utilisateur;

public class MatchHelper {

    public static List<MatchResult> sortMatchResult(Set<Utilisateur> users, Utilisateur user, Matching matcher) {
        return users.stream()
            .map(u -> matcher.matching(u, user))
            .filter(MatchResult::isSuccess)
            .sorted(Comparator.comparing(MatchResult::isSuccess))
            .collect(Collectors.toList());
    }

    static final Function<Set<Connaissance>, MatchResult> checkFunctionBooleanMatchResult = x -> {
        if (x.isEmpty())
            return new BooleanMatchResult(false);
        else
            return new BooleanMatchResult(true);
    };

    private MatchHelper() {
    }
}
