package com.affinitynow.app.user.controller;

import com.affinitynow.app.exceptions.UserNotFoundException;
import com.affinitynow.app.model.User;
import com.affinitynow.app.user.dto.UserDto;
import com.affinitynow.app.user.repository.UserRepository;
import com.affinitynow.app.user.service.UserService;
import com.affinitynow.app.user.service.matcher.IMatchResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    public static final String USERNOTFOUND = "User not found - ";

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
            .map(UserDto::fromEntity)
            .collect(Collectors.toList());
    }

    @GetMapping(value = "/{username}")
    public UserDto getUserByUsername(@PathVariable("username") String username) throws UserNotFoundException {
        User user =  userRepository.findByPseudo(username).orElseThrow(() -> new UserNotFoundException(USERNOTFOUND + username));
        return UserDto.fromEntity(user);
    }

    @PostMapping(value = "/knowledges")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto createNewUserWithKnowledges(@RequestBody UserDto dto) {
        User user = new User(dto.getPseudo(), dto.getLikedKnowledges(), dto.getSeekedKnowledges());
        User userCreated = userService.save(user);
        return UserDto.fromEntity(userCreated);
    }

    @PostMapping(value = "/{username}/match/{strategyName}")
    public List<IMatchResult<Object>> getUserMatchingList(@PathVariable("username") String username,
                                                          @PathVariable String strategyName,
                                                          @RequestBody Set<String> excludedTopics) throws UserNotFoundException {
        User user = userRepository.findByPseudo(username).orElseThrow(() -> new UserNotFoundException(USERNOTFOUND + username));
        Optional<Set<String>> topics = Optional.of(excludedTopics);
        return userRepository.findAll().stream()
            .filter(l -> !l.getPseudo().equals(user.getPseudo()))
            .map(o -> userService.matching(strategyName, user, o, topics))
            .flatMap(Optional::stream)
            .collect(Collectors.toList());
    }

//    @GetMapping(value = "/{username}/friend")
//    public Set<UserDto> getFriendListFromUser(@PathVariable("username") String username) {
//        return userRepository.findByPseudo(username)
//            .map(User::getFriends)
//            .stream()
//            .flatMap(Set::stream)
//            .map(UserDto::fromEntity)
//            .collect(Collectors.toSet());
//    }

//    @PutMapping(value = "/{username}/friend")
//    @ResponseBody
//    public UserDto addFriendToUserFriendList(@RequestBody UserDto dto, @PathVariable("username") String username) throws UserNotFoundException {
//        User user = userRepository.findByPseudo(username).orElseThrow(() -> new UserNotFoundException(USERNOTFOUND + username));
//        Optional<User> friend = userRepository.findByPseudo(dto.getPseudo());
//        if(friend.isEmpty())
//            throw new UserNotFoundException(USERNOTFOUND + dto.getPseudo());
//        userService.addToFriendList(user, friend.get());
//        userService.save(user);
//        return UserDto.fromEntity(user);
//    }
//
//    @DeleteMapping(value = "/{username}/friend/{friend}")
//    @ResponseBody
//    public UserDto removeFriendFromUserFriendList(@PathVariable("username") String username, @PathVariable("friend") String friend) throws UserNotFoundException {
//        User user = userRepository.findByPseudo(username).orElseThrow(() -> new UserNotFoundException(USERNOTFOUND + username));
//        User userFriend = userRepository.findByPseudo(friend).orElseThrow(() -> new UserNotFoundException(USERNOTFOUND + friend));
//        userService.removeFromFriendList(user, userFriend);
//        userService.save(user);
//        return UserDto.fromEntity(user);
//    }
}
