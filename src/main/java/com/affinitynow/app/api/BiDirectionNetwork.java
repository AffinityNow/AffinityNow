package com.affinitynow.app.api;

import com.affinitynow.app.model.User;

import java.util.Set;

public interface BiDirectionNetwork extends Network {
    Set<User> getReceivedInvite();
    Set<User> getSentInvite();
}
