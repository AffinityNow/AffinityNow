package com.affinitynow.app.utilisateur.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.affinitynow.app.model.Connaissance;
import com.affinitynow.app.model.Niveau;
// import com.affinitynow.app.model.RatedTopic;
// import com.affinitynow.app.model.RatedTopicKey;
import com.affinitynow.app.model.Topic;
import com.affinitynow.app.model.Utilisateur;
// import com.affinitynow.app.topic.repository.RatedTopicRepository;
import com.affinitynow.app.utilisateur.dto.RatedTopicDto;
import com.affinitynow.app.utilisateur.dto.UtilisateurDto;
import com.affinitynow.app.utilisateur.repository.UtilisateurRepository;
import com.affinitynow.app.utilisateur.service.matcher.MatchResult;
import com.affinitynow.app.utilisateur.service.matcher.Matching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurService {
    private final UtilisateurRepository userRepo;
    // private final RatedTopicRepository ratedRepo;
    @Autowired
    Map<String, Matching> matchingStrategy = new HashMap<>();


    public UtilisateurService(UtilisateurRepository userRepo /*RatedTopicRepository ratedRepo*/) {
        this.userRepo = userRepo;
        // this.ratedRepo = ratedRepo;
    }

    // public void save(UtilisateurDto newUser) {
    //     final Utilisateur u = userRepo.save(new Utilisateur().setPseudo(newUser.getPseudo()));
    //     for (RatedTopicDto r : newUser.getTopics()) {
    //         final RatedTopic rt = new RatedTopic()
    //                 .setTopic(new Topic().setId(r.getId()))
    //                 .setRating(r.getScore())
    //                 .setUser(u)
    //                 .setId(new RatedTopicKey().setTopicId(r.getId()).setUserId(u.getId()));
    //         ratedRepo.save(rt);
    //     }
    // }

    public Stream<Connaissance> connaissance(Utilisateur user) {
        Optional<Collection<Connaissance>> list = Optional.ofNullable(user.getConnaissances().values());
        Stream<Connaissance> rtr = Stream.empty();
        if(list.isPresent()) 
            rtr = list.get().stream();
        
        return rtr;
    }

    public boolean connait(Topic topic, Utilisateur user) {
       return  user.getConnaissances().get(topic.getName()) == null ? false : true;
    }

    public Optional<Niveau> niveau(Utilisateur user, Topic topic) {
        return Optional.ofNullable(user.getConnaissances().getOrDefault(topic.getName(), null).niveau());
    }

    public MatchResult matching(String strategyName, Utilisateur utilisateur, Utilisateur utilisateur2) {
        return this.matchingStrategy.get(strategyName).matching(utilisateur, utilisateur2);
    }
}
