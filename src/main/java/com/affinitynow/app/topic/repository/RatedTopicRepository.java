package com.affinitynow.app.topic.repository;

import com.affinitynow.app.model.RatedTopic;
import com.affinitynow.app.model.RatedTopicKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatedTopicRepository extends JpaRepository<RatedTopic, RatedTopicKey> {
}

