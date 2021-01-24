package com.affinitynow.app.utilisateur.service;

import com.affinitynow.app.model.RatedTopic;
import com.affinitynow.app.model.RatedTopicKey;
import com.affinitynow.app.model.Topic;
import com.affinitynow.app.model.Utilisateur;
import com.affinitynow.app.topic.repository.RatedTopicRepository;
import com.affinitynow.app.utilisateur.dto.RatedTopicDto;
import com.affinitynow.app.utilisateur.dto.UtilisateurDto;
import com.affinitynow.app.utilisateur.repository.UtilisateurRepository;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurService {
    private final UtilisateurRepository userRepo;
    private final RatedTopicRepository ratedRepo;

    public UtilisateurService(UtilisateurRepository userRepo, RatedTopicRepository ratedRepo) {
        this.userRepo = userRepo;
        this.ratedRepo = ratedRepo;
    }

    public void save(UtilisateurDto newUser) {
        final Utilisateur u = userRepo.save(new Utilisateur().setPseudo(newUser.getPseudo()));
        for (RatedTopicDto r : newUser.getTopics()) {
            final RatedTopic rt = new RatedTopic()
                    .setTopic(new Topic().setId(r.getId()))
                    .setRating(r.getScore())
                    .setUser(u)
                    .setId(new RatedTopicKey().setTopicId(r.getId()).setUserId(u.getId()));
            ratedRepo.save(rt);
        }
    }
}
