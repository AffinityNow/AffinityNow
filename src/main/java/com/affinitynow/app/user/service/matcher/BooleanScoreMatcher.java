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
public class BooleanScoreMatcher implements Matcher {

    @Autowired
    private UserService userService;
    IntPredicate isHigherThan3 = x -> x >= 3;

    @Override
    public Optional<IMatchResult<Boolean>> match(User utilisateur, User utilisateur1) {
        Optional<IMatchResult<Boolean>> rtr = Optional.empty();

        Set<Knowledge> intersection = userService.knownTopics(utilisateur)
            .filter(c -> userService.userKnowTopic(c.topic(), utilisateur1))
            .filter(p -> userService.level(utilisateur, p.topic()).map(Level::value).filter(isHigherThan3::test).isPresent()
                && userService.level(utilisateur1, p.topic()).map(Level::value).filter(isHigherThan3::test).isPresent())
            .collect(Collectors.toSet());

            if(!intersection.isEmpty())
                rtr = Optional.of(new BooleanMatchResult<>(intersection, utilisateur, utilisateur1, true));

        return rtr;
    }
}
