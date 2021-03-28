package com.affinitynow.app.user.service.matcher;

import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.Level;
import com.affinitynow.app.model.User;

import java.util.Set;

public interface ScoreMatcher extends Matcher{
    default double getUserTotalScore(User user, Set<Knowledge> intersection) {
        return user.getLikedKnowledges().values()
                .stream()
                .filter(intersection::contains)
                .map(Knowledge::getLevel)
                .mapToDouble(Level::value)
                .reduce(0.0, Double::sum);
    }
}
