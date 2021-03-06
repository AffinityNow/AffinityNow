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


    @PostMapping(value = "/knowledges")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto createNewUserWithKnowledges(@RequestBody UserDto dto) {
        User user = convertToEntity(dto);
        User userCreated = userService.save(user);
        return convertToDto(userCreated);
    }

    @GetMapping(value = "/{name}/match/{strategyName}")
    public List<IMatchResult<Object>> getUserMatchingList(@PathVariable("name") String name, @PathVariable String strategyName) throws UserNotFoundException {
        User user = userRepository.findByPseudo(name).orElseThrow(() -> new UserNotFoundException("User not found - " + name));
        return userRepository.findAll().stream()
                .filter(l -> !l.getPseudo().equals(user.getPseudo()))
                .collect(Collectors.toList())
                .stream()
                .map(o -> userService.matching(strategyName, user, o))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
     
        if (userDto.getId() != null) {
            if(userRepository.findById(userDto.getId()).isPresent()){
                User oldUser = userRepository.findById(userDto.getId()).get();
                user.setId(oldUser.getId());
            }
        }
        return user;
    }
}
