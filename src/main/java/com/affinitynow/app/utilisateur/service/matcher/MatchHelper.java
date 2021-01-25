package com.affinitynow.app.utilisateur.service.matcher;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.affinitynow.app.model.Utilisateur;

public class MatchHelper {
    
    public static Set<Utilisateur> sortMatchResult(Set<Utilisateur> users, Utilisateur user, Matching matcher) {
        // return users.stream()
        //     .map(u -> matcher.matching(u, user))
        //     .filter(mr -> mr.isSuccess())
        //     .collect(Collectors.toList());
    }
}
