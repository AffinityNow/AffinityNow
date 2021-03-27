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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("scoreDouble")
public class DoubleScoreMatcher implements ScoreMatcher {
    private final UserService userService;
    IntPredicate isHigherThan3 = x -> x >= 3;
    private Set<String> excludedTopics;

    @Autowired
    public DoubleScoreMatcher(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<IMatchResult<Double>> match(User user, User matchingUser) {
        Optional<IMatchResult<Double>> rtr = Optional.empty();
        Set<Knowledge> intersection = userService.listOfTopicsByType(user, "liked")
            .filter(c -> userService.isLikedTopic(c.topic(), matchingUser))
            .filter(e -> !isExcluded(e.getTopic().getName()))
            .filter(p -> userService.levelOfLikedTopic(user, p.topic()).map(Level::value).filter(isHigherThan3::test).isPresent()
                && userService.levelOfLikedTopic(matchingUser, p.topic()).map(Level::value).filter(isHigherThan3::test).isPresent())
            .collect(Collectors.toSet());
            if(!intersection.isEmpty())
                rtr = Optional.of(new DoubleMatchResult<>(intersection, user, matchingUser, (double) intersection.size(), calculateQuality(user,  matchingUser, intersection)));
        return rtr;
    }

    @Override
    public Double calculateQuality(User user, User matchingUser, Set<Knowledge> intersection) {
        return getUserTotalScore(user, intersection) + getUserTotalScore(matchingUser, intersection) / intersection.size();
    }

    @Override
    public boolean isExcluded(String topic) {
        return excludedTopics.contains(topic);
    }

    @Override
    public Set<String> getFilteredTopic() {
        return excludedTopics;
    }

    @Override
    public void setFilteredTopic(Set<String> topics) {
        excludedTopics = topics;
    }
}
