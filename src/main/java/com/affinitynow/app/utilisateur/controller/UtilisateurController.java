package com.affinitynow.app.utilisateur.controller;

import com.affinitynow.app.utilisateur.dto.UtilisateurDto;
import com.affinitynow.app.utilisateur.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.affinitynow.app.model.Utilisateur;
import com.affinitynow.app.utilisateur.repository.UtilisateurRepository;
@RestController
public class UtilisateurController {

    private final UtilisateurService userService;
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurController(UtilisateurService userService, UtilisateurRepository utilisateurRepository) {
        this.userService = userService;
        this.utilisateurRepository = utilisateurRepository;
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

    @GetMapping(value = "/utilisateur/{id}/match/{name}")
    public List<Utilisateur> getUtilisateurMatchingList(@PathVariable("id") Long id, @PathVariable String name) {
        // List<Utilisateur> rtr = new ArrayList<>();
        // utilisateurRepository.findAll().stream()
        //     .filter(l -> !l.getId().equals(id))
        //     .collect(Collectors.toList())
        //     .forEach(l -> {
        //         if(userService.matching(id, name, utilisateurRepository.findById(id).get()))
        //             rtr.add(l);
        //     });        
        // return rtr;

        return null;
    }
}
