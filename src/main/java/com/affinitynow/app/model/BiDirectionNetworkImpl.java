package com.affinitynow.app.model;

import com.affinitynow.app.api.BiDirectionNetwork;
import com.affinitynow.app.exceptions.FriendRequestNotFoundException;
import com.affinitynow.app.user.service.network.FriendRequestManager;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

public class BiDirectionNetworkImpl implements BiDirectionNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private User user;

    public FriendRequestManager getFriendRequestManager() {
        return friendRequestManager;
    }

    public void setFriendRequestManager(FriendRequestManager friendRequestManager) {
        this.friendRequestManager = friendRequestManager;
    }

    @ElementCollection
    private Set<User> friends;
    private FriendRequestManager friendRequestManager;

    @Override
    public void addToNetwork(User user) {
        friends.add(user);
    }

    @Override
    public void removeFromNetwork(User user) {
        friends.remove(user);
    }

    public void sendFriendRequest(User user, User sentRequestToUser) {
        this.friendRequestManager.sendFriendRequestInvite(user, sentRequestToUser);
    }

    public void manageFriendRequest(User user, boolean choice, User userToManage) {
        try {
            this.friendRequestManager.manageFriendRequestInvite(user, choice, userToManage);
        } catch (FriendRequestNotFoundException e) {
            e.getMessage();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @Override
    public Set<User> getReceivedInvite() {
        return this.friendRequestManager.getReceivedFriendInvite();
    }

    @Override
    public Set<User> getSentInvite() {
        return this.friendRequestManager.getSentFriendInvite();
    }
}
