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

@Component
public class DoubleScoreMatcher implements Matcher {

    @Autowired
    private UserService userService;
    IntPredicate isHigherThan3 = x -> x >= 3;

    @Override
    public Optional<IMatchResult<Double>> match(User user, User matchingUser) {
        Optional<IMatchResult<Double>> rtr = Optional.empty();

        Set<Knowledge> intersection = userService.knownTopics(user)
            .filter(c -> userService.userKnowTopic(c.topic(), matchingUser))
            .filter(p -> userService.level(user, p.topic()).map(Level::value).filter(isHigherThan3::test).isPresent()
                && userService.level(matchingUser, p.topic()).map(Level::value).filter(isHigherThan3::test).isPresent())
            .collect(Collectors.toSet());

            if(!intersection.isEmpty())
                rtr = Optional.of(new DoubleMatchResult<>(intersection, user, matchingUser, Double.valueOf(intersection.size())));

        return rtr;
    }
}
