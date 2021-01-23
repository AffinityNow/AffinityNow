package com.affinitynow.app.utilisateur.controller;

import com.affinitynow.app.utilisateur.dto.TopicDto;
import com.affinitynow.app.utilisateur.dto.UtilisateurDto;
import com.affinitynow.app.utilisateur.repository.UtilisateurRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

class UtilisateurControllerTest {
    @Mock private UtilisateurRepository repository;

    @InjectMocks
    private UtilisateurController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testcreateNewUtilisateurWithTopics() {
        // Given:
        final UtilisateurDto dto = new UtilisateurDto().setPseudo("ABC").setTopics(Set.of(new TopicDto().setName("T1")));
        Mockito.when(repository.save(any())).thenAnswer(invoc -> invoc.getArgument(0));

        // When:
        final UtilisateurDto created = controller.createNewUtilisateurWithTopics(dto);

        // Then:
        assertThat(created).usingRecursiveComparison()
                .isEqualTo(new UtilisateurDto().setPseudo("ABC").setTopics(Set.of(new TopicDto().setName("T1"))));
    }
}