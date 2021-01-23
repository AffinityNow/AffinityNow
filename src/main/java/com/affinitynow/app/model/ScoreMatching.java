package com.affinitynow.app.model;

import java.util.List;
public class ScoreMatching implements Matching {

    private List<Utilisateur> utilisateurs;

    public ScoreMatching(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    @Override
    public List<Utilisateur> matching(Utilisateur utilisateur1) {
        
    }

    public boolean userHasAtLeast4CommonTopics(Utilisateur utilisateur1, Utilisateur utilisateur2) {
        return utilisateur1.getTopics().keySet().stream()
                .filter(utilisateur2.getTopics().keySet()::contains)
                .count() >= 4;
    }
}
