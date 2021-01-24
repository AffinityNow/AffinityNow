package com.affinitynow.app.strategy;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.affinitynow.app.model.RatedTopic;
import com.affinitynow.app.model.Topic;
import com.affinitynow.app.model.Utilisateur;
import com.affinitynow.app.topic.repository.RatedTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("score")
public class ScoreMatching implements Matching {

    private List<Utilisateur> utilisateurs;
    @Autowired
    private RatedTopicRepository ratedTopicRepository;

    public ScoreMatching(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
    
    BiFunction<List<Topic>, List<Topic>, List<Topic>> commonTopicsBetweenTwoUsers = (x,y) ->
        x.stream()
            .filter(y::contains)
            .collect(Collectors.toList());
    
    Function<Utilisateur, List<Topic>> scoreIsAtLeastThreeForTopics = x ->
        ratedTopicRepository.findByUser(x.getId()).stream()
            .filter(v -> v.getRating() >= 3)
            .map(RatedTopic::getTopic)
            .collect(Collectors.toList());

    @Override
    public Boolean matching(Utilisateur utilisateur, Utilisateur utilisateur1) {
        Boolean rtr = false;
        List<Topic> interesection = commonTopicsBetweenTwoUsers.apply(scoreIsAtLeastThreeForTopics.apply(utilisateur),
            scoreIsAtLeastThreeForTopics.apply(utilisateur1));
        if(interesection.size() >=2)
            rtr =  true;
        return rtr;
    }
}
