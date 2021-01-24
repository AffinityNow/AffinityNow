package com.affinitynow.app.utilisateur.repository;

import com.affinitynow.app.model.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class FakeUtilisateurRepository implements UtilisateurRepository{

    @Override
    public Utilisateur save(Utilisateur user) {
        return user;
    }
}

