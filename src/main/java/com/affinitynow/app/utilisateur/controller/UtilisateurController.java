package com.affinitynow.app.utilisateur.controller;

import java.util.List;

import com.affinitynow.app.model.Topic;
import com.affinitynow.app.model.Utilisateur;
import com.affinitynow.app.utilisateur.dto.UtilisateurDto;
import com.affinitynow.app.utilisateur.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class UtilisateurController {

    @Autowired
    UtilisateurRepository repository;
    
    @PostMapping(value="/utilisateur")
    public Utilisateur createNewUtilisateur() {
        //TODO: process POST request
        return null;
    }

    @PostMapping(value="/utilisateur/{id}/topics")
    public void addTopicsForUserById(@PathVariable Long id,
                                     @PathVariable List<Topic> topics) {
        //TODO: process POST request
    }

    @DeleteMapping(value = "/utilisateur/{id}/topic/{name}")
    public void removeTopicById(@PathVariable Long id, @PathVariable String topicName) {
        //TODO: process DELETE request
    }
    
    @GetMapping(value="/utilisateur/{id}/match")
    public List<Utilisateur> getUtilisateurMatchingListById(@PathVariable Long id) {
        //TODO: process GET request
        return null;
    }
}
