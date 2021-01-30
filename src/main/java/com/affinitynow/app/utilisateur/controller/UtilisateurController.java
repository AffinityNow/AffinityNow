package com.affinitynow.app.utilisateur.controller;

import com.affinitynow.app.utilisateur.dto.UtilisateurDto;
import com.affinitynow.app.utilisateur.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import com.affinitynow.app.exceptions.UserNotFoundException;
import com.affinitynow.app.model.Utilisateur;
import com.affinitynow.app.utilisateur.repository.UtilisateurRepository;
@RestController
@CrossOrigin
public class UtilisateurController {

    private final UtilisateurService userService;
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurController(UtilisateurService userService, UtilisateurRepository utilisateurRepository) {
        this.userService = userService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping(value = "/utilisateur/connaissances")
    public ResponseEntity<UtilisateurDto> createNewUtilisateurWithConnaissance(@RequestBody UtilisateurDto dto) {
        userService.save(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/utilisateur/{id}/match/{name}")
    public List<Utilisateur> getUtilisateurMatchingList(@PathVariable("id") Long id, @PathVariable String name)
            throws UserNotFoundException {
        Utilisateur user = utilisateurRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found - " + id));
        return utilisateurRepository.findAll().stream()
                .filter(l -> !l.getId().equals(id))
                .collect(Collectors.toList())
                .stream()
                .filter(l -> userService.matching(name, user, l).isSuccess())
                .collect(Collectors.toList());
    }
}
