package com.affinitynow.app.user.service.matcher;

import java.util.Optional;
import java.util.Set;

import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.User;
import org.springframework.stereotype.Component;
@Component
public interface Matcher {
    <T> Optional<IMatchResult<T>> match(User user, User matchingUser);
    Double calculateQuality(User user, User matchingUser, Set<Knowledge> intersection);
}
