package com.affinitynow.app.user.controller;

import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.Topic;
import com.affinitynow.app.model.User;
import com.affinitynow.app.user.dto.UserDto;
import com.affinitynow.app.user.repository.UserRepository;
import com.affinitynow.app.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.when;

public class UserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserController userController;

    List<User> userList;
    List<UserDto> userListDTO;

    User user1;
    User user2;
    User user3;
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
        user1 = new User("Jeff", likedKnowledges, seekedKnowledges);
        user2 = new User("Aly", likedKnowledges, seekedKnowledges);
        user3 = new User("Mike", likedKnowledges, seekedKnowledges);
        user1.setId(1L);
        user2.setId(2L);
        user3.setId(2L);
        userList = new ArrayList<>();
        userListDTO = Arrays.asList(UserDto.fromEntity(user1).setId(1L),
                UserDto.fromEntity(user2).setId(2L),
                UserDto.fromEntity(user3).setId(3L));
        userList = Arrays.asList(user1,
                user2,
                user3);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEmptyList() {
        List<User> emptyList = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(emptyList);
        List<UserDto> expected = Collections.emptyList();

        List<UserDto> actual = userController.getAllUsers();

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(userList);
        List<UserDto> expected = userListDTO;

        List<UserDto> actual = userController.getAllUsers();

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
