package com.affinitynow.app.user.service.matcher;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.Level;
import com.affinitynow.app.model.User;
import com.affinitynow.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("seekedDouble")
public class DoubleSeekedTopicMatcher implements SeekedTopicMatcher {
    @Autowired
    private UserService userService;
    
    @Override
    public Optional<IMatchResult<Double>> match(User user, User matchingUser) {
        Optional<IMatchResult<Double>> rtr = Optional.empty();
        Set<Knowledge> intersection = userService.listOfTopicsByType(user, "seeked")
            .filter(c -> userService.isLikedTopic(c.topic(), matchingUser))
            .collect(Collectors.toSet());
        if(!intersection.isEmpty())
            rtr = Optional.of(new DoubleMatchResult<>(intersection, user, matchingUser, Double.valueOf(intersection.size()),  calculateQuality(user,  matchingUser, intersection)));
        return rtr;
    }

    @Override
    public Double calculateQuality(User user, User matchingUser, Set<Knowledge> intersection) {
        return getUserTotalScoreForKnowledge(user, intersection, "seeked") + getUserTotalScoreForKnowledge(matchingUser, intersection, "liked") / intersection.size();
    }

    double getUserTotalScoreForKnowledge(User user, Set<Knowledge> intersection, String type) {
        return switch (type) {
            case "liked" -> user.getLikedKnowledges().values()
                                    .stream()
                                    .filter(p -> intersection.contains(p))
                                    .map(Knowledge::getLevel)
                                    .mapToDouble(Level::value)
                                    .reduce(0.0, Double::sum);
            case "seeked" -> user.getSeekedKnowledges().values()
                                    .stream()
                                    .filter(p -> intersection.contains(p))
                                    .map(Knowledge::getLevel)
                                    .mapToDouble(Level::value)
                                    .reduce(0.0, Double::sum);
            default -> 0.0;
        };
    }
}
