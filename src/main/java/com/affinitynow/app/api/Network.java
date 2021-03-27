package com.affinitynow.app.api;

import com.affinitynow.app.model.User;

import javax.persistence.Embeddable;

@Embeddable
public interface Network {
    void addToNetwork(User user);
    void removeFromNetwork(User user);
}
