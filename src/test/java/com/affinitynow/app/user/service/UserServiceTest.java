package com.affinitynow.app.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.affinitynow.app.user.repository.UserRepository;
import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.Level;
import com.affinitynow.app.model.Topic;
import com.affinitynow.app.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class UserServiceTest {
    @InjectMocks
    UserService userService;
     
    @Mock
    UserRepository userRepository;

    User user1;
    User user2;
    User user3;
    Topic topic1 = new Topic("Football");
    Topic topic2 = new Topic("Food");
    Topic topic3 = new Topic("BasketBall");
    Topic topic4 = new Topic("Sport");
    Set<User> friendList;
    Map<String, Knowledge> likedKnowledges = Map.of(
        "Football", new Knowledge(topic1, "ONE"),
        "Food", new Knowledge(topic2, "FIVE")
    );
    Map<String, Knowledge> seekedKnowledges = Map.of(
        "BasketBall", new Knowledge(topic3, "ONE"),
        "Sport", new Knowledge(topic4, "FIVE")
    );
 
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        user1 = new User();
        user2 = new User();
        user3 = new User();
        user1.setLikedKnowledges(likedKnowledges);
        user1.setSeekedKnowledges(seekedKnowledges);
        friendList = new HashSet<>();
        friendList.add(user2);
        user1.setFriends(friendList);
    }

    @Test
    public void saveTest() {
        userService.save(user1);
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    public void listOfTopicsByTypeTestShouldBeLikedTopics() {
        List<Knowledge> actual = userService.listOfTopicsByType(user1, "liked").collect(Collectors.toList());
        List<Knowledge> expected = likedKnowledges.values().stream().collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void listOfTopicsByTypeTestShouldBeSeekedTopics() {
        List<Knowledge> actual = userService.listOfTopicsByType(user1, "seeked").collect(Collectors.toList());
        List<Knowledge> expected = seekedKnowledges.values().stream().collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void listOfTopicsByTypeTestShouldBeEmpty() {
        List<Knowledge> actual = userService.listOfTopicsByType(user1, "test").collect(Collectors.toList());
        List<Object> expected = Stream.empty().collect(Collectors.toList());
       assertEquals(expected, actual);
    }

    @Test
    public void isLikedTopicShouldBeTrue() {
        assertEquals(true, userService.isLikedTopic(topic1, user1));
    }

    @Test
    public void isLikedTopicShouldBeFalse() {
        assertEquals(false, userService.isLikedTopic(topic3, user1));
    }

    @Test
    public void isSeekedTopicShouldBeTrue() {
        assertEquals(true, userService.isSeekedTopic(topic3, user1));
    }
    
    @Test
    public void isSeekedTopicShouldBeFalse() {
        assertEquals(false, userService.isSeekedTopic(topic1, user1));
    }

    @Test
    public void levelOfTopicTestShouldReturnNull() {
        assertEquals(Optional.of(Level.ONE),userService.levelOfLikedTopic(user1, topic1));
    }
    
    @Test
    public void addToFriendListTest(){
        userService.addToFriendList(user1, user3);
        Set<User> friends = user1.getFriends();
        assertEquals(true, friends.contains(user3));
    }

    @Test
    public void removeFromFriendListTest(){
        userService.removeFromFriendList(user1, user3);
        Set<User> friends = user1.getFriends();
        assertEquals(false, friends.contains(user3));
    }

    @Test
    public void getFriendListTest() {
        assertEquals(friendList, userService.getFriendList(user1));
    }
}
