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
import com.affinitynow.app.user.service.matcher.IMatchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
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
    Set<User> friendList;
    Map<String, Knowledge> likedKnowledge = new HashMap<>();
    Map<String, Knowledge> seekedKnowledge = new HashMap<>();
 
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        user1 = new User();
        user2 = new User();
        user3 = new User();
        likedKnowledge.put("Football", new Knowledge(topic1, "ONE"));
        likedKnowledge.put("Food", new Knowledge(topic2, "FIVE"));
        seekedKnowledge.put("BasketBall", new Knowledge(topic3, "ONE"));
        seekedKnowledge.put("Sport", new Knowledge(topic4, "FIVE"));
        user1.setLikedKnowledges(likedKnowledge);
        user1.setSeekedKnowledges(seekedKnowledge);
        user2.setLikedKnowledges(likedKnowledge);
        user2.setSeekedKnowledges(seekedKnowledge);
        followList = new HashSet<>();
        friendList = new HashSet<>();
        followList.add(user2);
        friendList.add(user2);
        user1.setFollows(followList);
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
        List<Knowledge> expected = new ArrayList<>(likedKnowledge.values());
        assertEquals(expected, actual);
    }

    @Test
    public void listOfTopicsByTypeTestShouldBeSeekedTopics() {
        List<Knowledge> actual = userService.listOfTopicsByType(user1, "seeked").collect(Collectors.toList());
        List<Knowledge> expected = new ArrayList<>(seekedKnowledge.values());
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
        userService.unFollowUser(user1, user2);
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
        userService.removeFromFriendList(user1, user2);
        Set<User> friends = user1.getFollows();
        assertFalse(friends.contains(user3) && user3.getFriends().contains(user1));
    }

    @Test
    public void getFriendListTest() {
        assertEquals(user1.getFriends(), userService.getFriendList(user1));
    }

    @Test
    public <T> void shouldMatchUserWithScoreBooleanMethodWithNoExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        final Optional<IMatchResult<T>> matching = userService.matching("scoreBool", user1, user2, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent())
            commonTopics = matching.get().commonTopicsBetweenUsers();
        assertFalse(matching.isEmpty());
    }

    @Test
    public <T> void shouldMatchUserWithScoreBooleanMethodWithExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        excludedTopics.map(e -> e.add("FootBall"));
        final Optional<IMatchResult<T>> matching = userService.matching("scoreBool", user1, user2, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent())
            commonTopics = matching.get().commonTopicsBetweenUsers();
        assertFalse(matching.isEmpty());
    }

    @Test
    public <T> void shouldNotMatchUserWithScoreBooleanMethodWithNoExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        final Optional<IMatchResult<T>> matching = userService.matching("scoreBool", user1, user3, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent())
            commonTopics = matching.get().commonTopicsBetweenUsers();
        assertTrue(matching.isEmpty());
    }

    @Test
    public <T> void shouldNotMatchUserWithScoreBooleanMethodWithExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        excludedTopics.map(e -> e.add("Food"));
        final Optional<IMatchResult<T>> matching = userService.matching("scoreBool", user1, user3, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent())
            commonTopics = matching.get().commonTopicsBetweenUsers();
        assertTrue(matching.isEmpty());
    }


    @Test
    public <T> void shouldMatchUserWithScoreDoubleMethodWithNoExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        final Optional<IMatchResult<T>> matching = userService.matching("scoreDouble", user1, user2, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent()) {
            commonTopics = matching.get().commonTopicsBetweenUsers();
        }
        assertFalse(matching.isEmpty());
    }

    @Test
    public <T> void shouldMatchUserWithScoreDoubleMethodWithExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        excludedTopics.map(e -> e.add("FootBall"));
        final Optional<IMatchResult<T>> matching = userService.matching("scoreDouble", user1, user2, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent())
            commonTopics = matching.get().commonTopicsBetweenUsers();
        assertFalse(matching.isEmpty());
    }

    @Test
    public <T> void shouldNotMatchUserWithScoreDoubleMethodWithNoExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        final Optional<IMatchResult<T>> matching = userService.matching("scoreDouble", user1, user3, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent())
            commonTopics = matching.get().commonTopicsBetweenUsers();
        assertTrue(matching.isEmpty());
    }

    @Test
    public <T> void shouldNotMatchUserWithScoreDoubleMethodWithExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        excludedTopics.map(e -> e.add("Food"));
        final Optional<IMatchResult<T>> matching = userService.matching("scoreDouble", user1, user3, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent())
            commonTopics = matching.get().commonTopicsBetweenUsers();
        assertTrue(matching.isEmpty());
    }

    @Test
    public <T> void shouldMatchUserWithSeekedDoubleMethodWithNoExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        user1.getSeekedKnowledges().put("Reading", new Knowledge(new Topic("Reading"), "TWO"));
        user2.getLikedKnowledges().put("Reading", new Knowledge(new Topic("Reading"), "TWO"));
        final Optional<IMatchResult<T>> matching = userService.matching("seekedDouble", user1, user2, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent()) {
            commonTopics = matching.get().commonTopicsBetweenUsers();
        }
        assertFalse(matching.isEmpty());
    }

    @Test
    public <T> void shouldMatchUserWithSeekedDoubleMethodWithExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        user1.getSeekedKnowledges().put("Reading", new Knowledge(new Topic("Reading"), "TWO"));
        user2.getLikedKnowledges().put("Reading", new Knowledge(new Topic("Reading"), "TWO"));
        excludedTopics.map(e -> e.add("Food"));
        final Optional<IMatchResult<T>> matching = userService.matching("seekedDouble", user1, user2, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent())
            commonTopics = matching.get().commonTopicsBetweenUsers();
        assertFalse(matching.isEmpty());
    }

    @Test
    public <T> void shouldNotMatchUserWithSeekedDoubleMethodWithNoExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        user1.getSeekedKnowledges().put("Reading", new Knowledge(new Topic("Reading"), "TWO"));
        user3.getLikedKnowledges().put("Hiking", new Knowledge(new Topic("Hiking"), "FIVE"));
        final Optional<IMatchResult<T>> matching = userService.matching("seekedDouble", user1, user3, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent())
            commonTopics = matching.get().commonTopicsBetweenUsers();
        assertTrue(matching.isEmpty());
    }

    @Test
    public <T> void shouldNotMatchUserWithSeekedDoubleMethodWithExcludedTopics() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        user1.getSeekedKnowledges().put("Reading", new Knowledge(new Topic("Reading"), "TWO"));
        user3.getLikedKnowledges().put("Reading", new Knowledge(new Topic("Reading"), "TWO"));
        excludedTopics.map(e -> e.add("Reading"));
        final Optional<IMatchResult<T>> matching = userService.matching("seekedDouble", user1, user3, excludedTopics );
        Set<Knowledge> commonTopics;
        if (matching.isPresent())
            commonTopics = matching.get().commonTopicsBetweenUsers();
        assertTrue(matching.isEmpty());
    }

    @Test
    public <T> void scoreBooleanMethodShouldHaveAQuality() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        final Optional<IMatchResult<T>> matching = userService.matching("scoreBool", user1, user2, excludedTopics );
        double quality = 0;
        if (matching.isPresent())
            quality = matching.get().quality();
        assertTrue(quality != 0);
    }

    @Test
    public <T> void scoreDoubleMethodShouldHaveAQuality() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        final Optional<IMatchResult<T>> matching = userService.matching("scoreDouble", user1, user2, excludedTopics );
        double quality = 0;
        if (matching.isPresent())
            quality = matching.get().quality();
        assertTrue(quality != 0);
    }

    @Test
    public <T> void seekedDoubleMethodShouldHaveAQuality() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        user1.getSeekedKnowledges().put("Reading", new Knowledge(new Topic("Reading"), "TWO"));
        user3.getLikedKnowledges().put("Reading", new Knowledge(new Topic("Reading"), "TWO"));
        final Optional<IMatchResult<T>> matching = userService.matching("seekedDouble", user1, user3, excludedTopics );
        double quality = 0;
        if (matching.isPresent())
            quality = matching.get().quality();
        assertTrue(quality != 0);
    }

    @Test
    public <T> void scoreBooleanMethodShouldHaveAResult() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        final Optional<IMatchResult<T>> matching = userService.matching("scoreBool", user1, user2, excludedTopics );
        T result = null;
        if (matching.isPresent())
            result = matching.get().result();
        assertNotNull(result);
    }

    @Test
    public <T> void scoreDoubleMethodShouldHaveAResult() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        final Optional<IMatchResult<T>> matching = userService.matching("scoreDouble", user1, user2, excludedTopics );
        T result = null;
        if (matching.isPresent())
            result = matching.get().result();
        assertNotNull(result);
    }

    @Test
    public <T> void seekedDoubleMethodShouldHaveAResult() {
        Optional<Set<String>> excludedTopics = Optional.of(new HashSet<>());
        user1.getSeekedKnowledges().put("Reading", new Knowledge(new Topic("Reading"), "TWO"));
        user3.getLikedKnowledges().put("Reading", new Knowledge(new Topic("Reading"), "TWO"));
        final Optional<IMatchResult<T>> matching = userService.matching("seekedDouble", user1, user3, excludedTopics );
        T result = null;
        if (matching.isPresent())
            result = matching.get().result();
        assertNotNull(result);
    }
}
