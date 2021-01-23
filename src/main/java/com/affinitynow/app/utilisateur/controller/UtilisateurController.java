package com.affinitynow.app.utilisateur.controller;

import com.affinitynow.app.model.Utilisateur;
import com.affinitynow.app.utilisateur.dto.UtilisateurDto;
import com.affinitynow.app.utilisateur.repository.UtilisateurRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtilisateurController {

    private final UtilisateurRepository repository;

    public UtilisateurController(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/utilisateur")
    public UtilisateurDto createNewUtilisateur() {
        //TODO: process POST request
        return null;
    }

    @PostMapping(value = "/utilisateur/topics")
    public UtilisateurDto createNewUtilisateurWithTopics(@RequestBody UtilisateurDto dto) {
        Utilisateur created = repository.save(dto.toUtilisateur());
        return UtilisateurDto.from(created);
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
