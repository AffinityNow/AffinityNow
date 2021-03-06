package com.affinitynow.app.user.service.matcher;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.Level;
import com.affinitynow.app.model.User;
import com.affinitynow.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("seekedDouble")
public class DoubleSeekedTopicMatcher implements SeekedTopicMatcher {

    private final UserService userService;
    public static final String SEEKED = "seeked";
    public static final String LIKED = "liked";
    private Set<String> excludedTopics;

    @Autowired
    public DoubleSeekedTopicMatcher(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<IMatchResult<Double>> match(User user, User matchingUser) {
        Optional<IMatchResult<Double>> rtr = Optional.empty();
        Set<Knowledge> intersection = userService.listOfTopicsByType(user, SEEKED)
            .filter(c -> userService.isLikedTopic(c.topic(), matchingUser))
            .filter(e -> !isExcluded(e.getTopic().getName()))
            .collect(Collectors.toSet());
        if(!intersection.isEmpty())
            rtr = Optional.of(new DoubleMatchResult<>(intersection, user, matchingUser, Double.valueOf(intersection.size()),  calculateQuality(user,  matchingUser, intersection)));
        return rtr;
    }

    @Override
    public Double calculateQuality(User user, User matchingUser, Set<Knowledge> intersection) {
        return getUserTotalScoreForKnowledge(user, intersection, SEEKED) + getUserTotalScoreForKnowledge(matchingUser, intersection, LIKED) / intersection.size();
    }

    double getUserTotalScoreForKnowledge(User user, Set<Knowledge> intersection, String type) {
        return switch (type) {
            case LIKED -> user.getLikedKnowledges().values()
                                    .stream()
                                    .filter(p -> intersection.contains(p))
                                    .map(Knowledge::getLevel)
                                    .mapToDouble(Level::value)
                                    .reduce(0.0, Double::sum);
            case SEEKED -> user.getSeekedKnowledges().values()
                                    .stream()
                                    .filter(p -> intersection.contains(p))
                                    .map(Knowledge::getLevel)
                                    .mapToDouble(Level::value)
                                    .reduce(0.0, Double::sum);
            default -> 0.0;
        };
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
