package com.affinitynow.app.user.service.matcher;

import java.util.Set;

import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.User;

public abstract class AbstractMatchResult<T> implements IMatchResult<T> {
  private final User user;
  private final User mUser;
  private final Set<Knowledge> commonTopics;

  @Override
  public User user() {
    return user;
  }

  @Override
  public User mUSer() {
    return mUser;
  }

  protected AbstractMatchResult(Set<Knowledge> commonTopics, User user, User mUser) {
    this.commonTopics = commonTopics;
    this.user = user;
    this.mUser = mUser;
  }

  @Override
  public Set<Knowledge> commonTopicsBetweenUsers() {
    return commonTopics;
  }

  @Override
  public Double quality(){
    return null;
  }

  public User getUser() {
    return user;
  }

  public User getmUser() {
    return mUser;
  }

  public Set<Knowledge> getCommonTopics() {
    return commonTopics;
  }
}
