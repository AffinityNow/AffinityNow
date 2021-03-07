package com.affinitynow.app.user.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.affinitynow.app.exceptions.UserNotFoundException;
import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.Level;
import com.affinitynow.app.model.Topic;
import com.affinitynow.app.model.User;
import com.affinitynow.app.user.repository.UserRepository;
import com.affinitynow.app.user.service.matcher.IMatchResult;
import com.affinitynow.app.user.service.matcher.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private final UserRepository userRepo;
    @Autowired
    Map<String, Matcher> matcherMap =  new HashMap<>();

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User save(User newUser) {
        return userRepo.save(newUser);
    }

    public Stream<Knowledge> listOfTopicsByType(User user, String type) {
        Stream<Knowledge> rtr = Stream.empty();
        Optional<Collection<Knowledge>> list = switch(type) {
            case "liked" -> Optional.ofNullable(user.getLikedKnowledges().values());
            case "seeked" -> Optional.ofNullable(user.getSeekedKnowledges().values());
            default -> Optional.empty();
        };
        if(list.isPresent()) 
            rtr = list.get().stream();
        return rtr;
    }

    public boolean isLikedTopic(Topic topic, User user) {
       return  user.getLikedKnowledges().get(topic.getName()) == null ? false : true;
    }
    
    public boolean isSeekedTopic(Topic topic, User user) {
       return  user.getSeekedKnowledges().get(topic.getName()) == null ? false : true;
    }

    public Optional<Level> levelOfLikedTopic(User user, Topic topic) {
        return Optional.ofNullable(user.getLikedKnowledges().getOrDefault(topic.getName(), null).level());
    }

    public <T> Optional<IMatchResult<T>> matching(String strategyName, User user, User matchingUser){
        return this.matcherMap.get(strategyName).match(user, matchingUser);
    }

    public void addToFriendList(User user, User friend) {
       user.getFriends().add(friend);
    }

    public void removeFromFriendList(User user, User friend) {
        user.getFriends().remove(friend);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Set<User> getFriendList(User user){
        return user.getFriends();
    }

    public User subscrided(String username) throws UserNotFoundException {
        return userRepo.findByPseudo(username).orElseThrow(() -> new UserNotFoundException("User not found - " + username ));
    }
}

