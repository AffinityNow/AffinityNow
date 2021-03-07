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

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    public static final String USERNOTFOUND = "User not found - ";

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
            .map(p -> convertToDto(p))
            .collect(Collectors.toList());
    }

    @GetMapping(value = "/{username}")
    public UserDto getUserByUsername(@PathVariable("username") String username) throws UserNotFoundException {
        User user =  userRepository.findByPseudo(username).orElseThrow(() -> new UserNotFoundException(USERNOTFOUND + username));
        return convertToDto(user);
    }

    @PostMapping(value = "/knowledges")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto createNewUserWithKnowledges(@RequestBody UserDto dto) {
        User user = convertToEntity(dto);
        User userCreated = userService.save(user);
        return convertToDto(userCreated);
    }

    @GetMapping(value = "/{username}/match/{strategyName}")
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

    @GetMapping(value = "/{username}/friend")
    public Set<UserDto> getFriendListFromUser(@PathVariable("username") String username) {
        return userRepository.findByPseudo(username)
            .map(User::getFriends)
            .stream()
            .flatMap(Set::stream)
            .map(a -> convertToDto(a))
            .collect(Collectors.toSet());
    }

    @PutMapping(value = "/{username}/friend")
    @ResponseBody
    public UserDto addFriendToUserFriendList(@RequestBody UserDto dto, @PathVariable("username") String username) throws UserNotFoundException {
        User user = userRepository.findByPseudo(username).orElseThrow(() -> new UserNotFoundException(USERNOTFOUND + username));
        Optional<User> friend = userRepository.findByPseudo(dto.getPseudo());
        if(friend.isPresent())
            userService.addToFriendList(user, friend.get());
        else
            throw new UserNotFoundException(USERNOTFOUND + dto.getPseudo()); 
        userService.save(user);
        return convertToDto(user);
    }

    @DeleteMapping(value = "/{username}/friend/{friend}")
    @ResponseBody
    public UserDto removeFriendFromUserFriendList(@PathVariable("username") String username, @PathVariable("friend") String friend) throws UserNotFoundException {
        User user = userRepository.findByPseudo(username).orElseThrow(() -> new UserNotFoundException(USERNOTFOUND + username));
        User userFriend = userRepository.findByPseudo(friend).orElseThrow(() -> new UserNotFoundException(USERNOTFOUND + friend));
        userService.removeFromFriendList(user, userFriend);
        userService.save(user);
        return convertToDto(user);
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (userDto.getId() != null) {
            Optional<User> oldUser = userRepository.findById(userDto.getId());
            if(oldUser.isPresent()){
                user.setId(oldUser.get().getId());
            }
        }
        return user;
    }
}
