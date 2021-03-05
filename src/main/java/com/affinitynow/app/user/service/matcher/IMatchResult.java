package com.affinitynow.app.user.service.matcher;

import java.util.Set;

import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.User;

public interface IMatchResult<T> extends Comparable<IMatchResult<T>> {
    T result();
    User user();
    User mUSer();
    double quality();
    Set<Knowledge> commonTopicsBetweenUsers();
}
