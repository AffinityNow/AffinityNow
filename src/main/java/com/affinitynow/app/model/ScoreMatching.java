package com.affinitynow.app.model;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class ScoreMatching implements Matching {

    private List<Utilisateur> utilisateurs;

    public ScoreMatching(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    @Override
    public List<Utilisateur> matching(Utilisateur utilisateur1) {
        return null;
    }

    BiFunction<Utilisateur, Utilisateur, List<RatedTopic>> commontRatedTopicsBetweenTwoUsers = (x,y) -> 
        x.getRatedTopics().stream()
        .flatMap(l -> y.getRatedTopics().stream()
                .filter(p -> l.topic.equals(p.topic)))
        .collect(Collectors.toList());

    Predicate<List<RatedTopic>> userHasAtLeast4CommonTopics = x -> x.size() > 4;
}
