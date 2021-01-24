package com.affinitynow.app.topic.repository;

import com.affinitynow.app.model.Topic;
import com.affinitynow.app.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}

