package com.affinitynow.app.user.repository;

import java.util.Optional;

import com.affinitynow.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByPseudo(String name);
}

