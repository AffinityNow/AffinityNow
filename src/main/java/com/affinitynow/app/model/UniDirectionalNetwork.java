package com.affinitynow.app.model;

import com.affinitynow.app.api.Network;

import javax.persistence.*;
import java.util.Set;

@Embeddable
public class UniDirectionalNetwork implements Network {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    private Set<User> following;

    @Override
    public void addToNetwork(User user) {
        following.add(user);
    }

    @Override
    public void removeFromNetwork(User user) {
        following.remove(user);
    }
}
