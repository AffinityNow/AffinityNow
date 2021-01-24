package com.affinitynow.app.utilisateur.controller;

import com.affinitynow.app.utilisateur.dto.UtilisateurDto;
import com.affinitynow.app.utilisateur.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtilisateurController {

    private final UtilisateurService userService;

    public UtilisateurController(UtilisateurService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/utilisateur")
    public UtilisateurDto createNewUtilisateur() {
        //TODO: process POST request
        return null;
    }

    @CrossOrigin
    @PostMapping(value = "/utilisateur/topics")
    public ResponseEntity<UtilisateurDto> createNewUtilisateurWithTopics(@RequestBody UtilisateurDto dto) {
        userService.save(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/utilisateur/{id}/topic/{name}")
    public void removeTopicById(@PathVariable Long id, @PathVariable String topicName) {
        //TODO: process DELETE request
    }

    @GetMapping(value = "/utilisateur/{id}/match")
    public List<UtilisateurDto> getUtilisateurMatchingListById(@PathVariable Long id) {
        //TODO: process GET request
        return List.of();
    }
}
