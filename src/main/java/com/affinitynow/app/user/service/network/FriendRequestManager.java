package com.affinitynow.app.user.service.network;

import com.affinitynow.app.exceptions.FriendRequestNotFoundException;
import com.affinitynow.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FriendRequestManager {
    private Map<User, Set<User>>sentFriendInvite;
    private Map<User, Set<User>> receivedFriendInvite;
    private final EmailService emailService;

    @Autowired
    public FriendRequestManager(EmailService emailService) {
        this.emailService = emailService;
        this.sentFriendInvite = new HashMap<>();
        this.receivedFriendInvite = new HashMap<>();
    }

    public void sendFriendRequestInvite(User user, User sendRequestToUser) {

        sendRequestToUser.getBiDirectionNetwork().getReceivedInvite().add(user);
        user.getBiDirectionNetwork().getSentInvite().add(sendRequestToUser);
//        String mailBody = new StringBuilder().append("User")
//                .append(user.getPseudo())
//                .append("wants to be your friend")
//                .toString();
//        Mail mailtoSend = new Mail(user.getMail(), sendRequestToUser.getMail(), mailBody, "Friend request");
//        emailService.sendMail(mailtoSend);
    }

    public void manageFriendRequestInvite(User user, boolean choice, User userToManage) throws FriendRequestNotFoundException {
        if(!user.getBiDirectionNetwork().getReceivedInvite().contains(userToManage))
            throw new FriendRequestNotFoundException("Demande d'ami non trouvée pour l'utilisateur" + user.getPseudo());
        if(choice) {
            user.getBiDirectionNetwork().addToNetwork(userToManage);
            user.getBiDirectionNetwork().getSentInvite().remove(userToManage);
            userToManage.getBiDirectionNetwork().getReceivedInvite().remove(user);
        }
    }
}
