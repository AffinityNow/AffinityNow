package com.affinitynow.app.user.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.Level;
import com.affinitynow.app.model.Topic;
import com.affinitynow.app.model.User;
import com.affinitynow.app.user.dto.UserDto;
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

    public void save(UserDto newUser) {
        userRepo.save(new User(newUser.getPseudo(), newUser.getConnaissances()));
    }

    public Stream<Knowledge> knownTopics(User user) {
        Optional<Collection<Knowledge>> list = Optional.ofNullable(user.getKnowledge().values());
        Stream<Knowledge> rtr = Stream.empty();
        if(list.isPresent()) 
            rtr = list.get().stream();
        
        return rtr;
    }

    public boolean userKnowTopic(Topic topic, User user) {
       return  user.getKnowledge().get(topic.getName()) == null ? false : true;
    }

    public Optional<Level> level(User user, Topic topic) {
        return Optional.ofNullable(user.getKnowledge().getOrDefault(topic.getName(), null).level());
    }

    public <T> Optional<IMatchResult<T>> matching(String strategyName, User user, User matchingUser){
        return this.matcherMap.get(strategyName).match(user, matchingUser);
    }
}
