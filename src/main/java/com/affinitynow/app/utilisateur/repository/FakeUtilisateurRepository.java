package com.affinitynow.app.utilisateur.repository;

import com.affinitynow.app.model.Utilisateur;
import com.affinitynow.app.utilisateur.dto.UtilisateurDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    
}

