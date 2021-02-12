package com.affinitynow.app.topic.controller;

import com.affinitynow.app.model.Topic;
import com.affinitynow.app.topic.dto.TopicDto;
import com.affinitynow.app.topic.repository.TopicRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

class TopicControllerTest {
    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicController controllerToTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doitRetournerUneListeVideDeTopic() {
        // Given:
        // il n'y a pas de topic en base
        List<Topic> listeVide = new ArrayList<>();
        when(topicRepository.findAll()).thenReturn(listeVide);
        // expected:
        List<TopicDto> expected = Collections.emptyList();

        // When:
        // on appelle le controller
        List<TopicDto> actual = controllerToTest.getTopics();

        // Then:
        // Le résultat est une liste vide!
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }


    @Test
    void doitRetournerUneListeDeTopic() {
        // Given:
        List<Topic> listeTopics = new ArrayList<>();
        listeTopics.add(new Topic().setId(1L).setName("Comic book"));
        listeTopics.add(new Topic().setId(2L).setName("Sport"));
        listeTopics.add(new Topic().setId(3L).setName("Fashion"));

        when(topicRepository.findAll()).thenReturn(listeTopics);

        // expected:
        List<TopicDto> expected = Arrays.asList(
                new TopicDto().setId(1L).setName("Comic book"),
                new TopicDto().setId(2L).setName("Sport"),
                new TopicDto().setId(3L).setName("Fashion")
        );

        // When:
        // on appelle le controller
        List<TopicDto> actual = controllerToTest.getTopics();

        // Then:
        Assertions.assertThat(actual)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expected);
    }
}