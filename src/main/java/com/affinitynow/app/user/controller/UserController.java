package com.affinitynow.app.user.controller;

import com.affinitynow.app.exceptions.UserNotFoundException;
import com.affinitynow.app.model.User;
import com.affinitynow.app.user.dto.UserDto;
import com.affinitynow.app.user.repository.UserRepository;
import com.affinitynow.app.user.service.UserService;
import com.affinitynow.app.user.service.matcher.IMatchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/utilisateur")
public class UserController {

    @Autowired
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository utilisateurRepository) {
        this.userService = userService;
        this.userRepository = utilisateurRepository;
    }

    @PostMapping(value = "/knowledges")
    public ResponseEntity<UserDto> createNewUtilisateurWithConnaissance(@RequestBody UserDto dto) {
        userService.save(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}/match/{strategyName}")
    public List<IMatchResult<Object>> getUtilisateurMatchingList(@PathVariable("id") Long id, @PathVariable String strategyName) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found - " + id));
        return userRepository.findAll().stream()
                .filter(l -> !l.getId().equals(id))
                .collect(Collectors.toList())
                .stream()
                .map(o -> userService.matching(strategyName, user, o))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }
}
