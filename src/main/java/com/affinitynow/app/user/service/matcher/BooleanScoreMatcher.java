package com.affinitynow.app.user.service.matcher;

import java.util.Optional;
import java.util.Set;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.Level;
import com.affinitynow.app.model.User;
import com.affinitynow.app.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("scoreBool")
public class BooleanScoreMatcher implements ScoreMatcher {

    @Autowired
    private UserService userService;
    IntPredicate isHigherThan3 = x -> x >= 3;

    @Override
    public Optional<IMatchResult<Boolean>> match(User user, User matchingUser) {
        Optional<IMatchResult<Boolean>> rtr = Optional.empty();
        Set<Knowledge> intersection = userService.listOfTopicsByType(user, "liked")
            .filter(c -> userService.isLikedTopic(c.topic(), matchingUser))
            .filter(p -> userService.levelOfLikedTopic(user, p.topic()).map(Level::value).filter(isHigherThan3::test).isPresent()
                && userService.levelOfLikedTopic(matchingUser, p.topic()).map(Level::value).filter(isHigherThan3::test).isPresent())
            .collect(Collectors.toSet());
        if(!intersection.isEmpty())
            rtr = Optional.of(new BooleanMatchResult<>(intersection, user, matchingUser, true,  calculateQuality(user,  matchingUser, intersection)));
        return rtr;
    }

    @Override
    public Double calculateQuality(User user, User matchingUser, Set<Knowledge> intersection) {
        return getUserTotalScore(user, intersection) + getUserTotalScore(matchingUser, intersection) / intersection.size();
    }

    double getUserTotalScore(User user, Set<Knowledge> intersection) {
        return user.getLikedKnowledges().values()
                .stream()
                .filter(p -> intersection.contains(p))
                .map(Knowledge::getLevel)
                .mapToDouble(Level::value)
                .reduce(0.0, Double::sum);
    }
}