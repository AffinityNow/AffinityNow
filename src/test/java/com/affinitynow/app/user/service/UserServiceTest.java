package com.affinitynow.app.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.*;
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
    Set<User> followList;
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
        followList = new HashSet<>();
        followList.add(user2);
    }

    @Test
    public void saveTest() {
        userService.save(user1);
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    public void listOfTopicsByTypeTestShouldBeLikedTopics() {
        List<Knowledge> actual = userService.listOfTopicsByType(user1, "liked").collect(Collectors.toList());
        List<Knowledge> expected = new ArrayList<>(likedKnowledges.values());
        assertEquals(expected, actual);
    }

    @Test
    public void listOfTopicsByTypeTestShouldBeSeekedTopics() {
        List<Knowledge> actual = userService.listOfTopicsByType(user1, "seeked").collect(Collectors.toList());
        List<Knowledge> expected = new ArrayList<>(seekedKnowledges.values());
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
        assertTrue(userService.isLikedTopic(topic1, user1));
    }

    @Test
    public void isLikedTopicShouldBeFalse() {
        assertFalse(userService.isLikedTopic(topic3, user1));
    }

    @Test
    public void isSeekedTopicShouldBeTrue() {
        assertTrue(userService.isSeekedTopic(topic3, user1));
    }
    
    @Test
    public void isSeekedTopicShouldBeFalse() {
        assertFalse(userService.isSeekedTopic(topic1, user1));
    }

    @Test
    public void levelOfTopicTestShouldReturnNull() {
        assertEquals(Optional.of(Level.ONE),userService.levelOfLikedTopic(user1, topic1));
    }

    @Test
    public void addToFollowListTest(){
        userService.followUser(user1, user3);
        Set<User> friends = user1.getFollows();
        assertTrue(friends.contains(user3));
    }

    @Test
    public void removeFromFollowListTest(){
        userService.unFollowUser(user1, user3);
        Set<User> friends = user1.getFollows();
        assertFalse(friends.contains(user3));
    }

    @Test
    public void getFollowListTest() {
        assertEquals(followList, userService.getFollows(user1));
    }

    @Test
    public void addToFriendListTest(){
        userService.addToFriendList(user1, user3);
        Set<User> friends = user1.getFriends();
        assertTrue(friends.contains(user3) && user3.getFriends().contains(user1));
    }

    @Test
    public void removeFromFriendListTest(){
        userService.removeFromFriendList(user1, user3);
        Set<User> friends = user1.getFollows();
        assertFalse(friends.contains(user3) && user3.getFriends().contains(user1));
    }

    @Test
    public void getFriendListTest() {
        assertEquals(user1.getFriends(), userService.getFriendList(user1));
    }
}
