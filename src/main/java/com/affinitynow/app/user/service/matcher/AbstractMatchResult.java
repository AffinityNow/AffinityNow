package com.affinitynow.app.user.service.matcher;

import java.util.Set;

import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.User;

public abstract class AbstractMatchResult<T> implements IMatchResult<T> {
  protected final User user;
  protected final User mUser;
  protected final Set<Knowledge> commonTopics;
  protected java.lang.Double quality;

  @Override
  public User user() {
    return user;
  }

  @Override
  public User mUSer() {
    return mUser;
  }

  @Override
  public Set<Knowledge> commonTopicsBetweenUsers() {
    return commonTopics;
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

  public java.lang.Double getQuality() {
    return quality;
  }

  public void setQuality(java.lang.Double quality) {
    this.quality = quality;
  }

  @Override
  public double quality() {
    return quality;
  }

  protected AbstractMatchResult(User user, User mUser, Set<Knowledge> commonTopics, Double quality) {
    this.user = user;
    this.mUser = mUser;
    this.commonTopics = commonTopics;
    this.quality = quality;
  }

}
