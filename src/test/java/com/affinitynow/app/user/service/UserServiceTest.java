package com.affinitynow.app.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.affinitynow.app.user.repository.UserRepository;
import com.affinitynow.app.model.Knowledge;
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
    Topic topic1 = new Topic("Football");
    Topic topic2 = new Topic("Food");
    Topic topic3 = new Topic("BasketBall");
    Topic topic4 = new Topic("Sport");
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
        user1.setLikedKnowledges(likedKnowledges);
        user2.setLikedKnowledges(likedKnowledges);
        user1.setSeekedKnowledges(seekedKnowledges);
        user2.setSeekedKnowledges(seekedKnowledges);
    }

    @Test
    public void saveTest() {
        userService.save(user1);
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    public void listOfTopicsByTypeTestShouldReturnLikedTopics() {
        List<Knowledge> actual = userService.listOfTopicsByType(user1, "liked").collect(Collectors.toList());
        List<Knowledge> expected = likedKnowledges.values().stream().collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void listOfTopicsByTypeTestShouldReturnSeekedTopics() {
        List<Knowledge> actual = userService.listOfTopicsByType(user1, "seeked").collect(Collectors.toList());
        List<Knowledge> expected = seekedKnowledges.values().stream().collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void listOfTopicsByTypeTestShouldReturnEmpty() {
        List<Knowledge> actual = userService.listOfTopicsByType(user1, "test").collect(Collectors.toList());
        List<Object> expected = Stream.empty().collect(Collectors.toList());
       assertEquals(expected, actual);
    }

    public void isLikedTopicShouldReturnTrue() {
        assertEquals(true, userService.isLikedTopic(topic1, user1));
    }
    
    public void isLikedTopicShouldReturnFalse() {
        assertEquals(true, userService.isLikedTopic(topic3, user1));
    }
    
    public void isSeekedTopicShouldReturnTrue() {
        assertEquals(true, userService.isSeekedTopic(topic3, user1));
    }
    
    public void isSeekedTopicShouldReturnFalse() {
        assertEquals(true, userService.isSeekedTopic(topic1, user1));
    }
}
