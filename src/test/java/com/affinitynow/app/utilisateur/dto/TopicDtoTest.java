package com.affinitynow.app.utilisateur.dto;

import com.affinitynow.app.model.Topic;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TopicDtoTest {

    @Test
    void should_convert_to_model() {
        // Given:
        final TopicDto dto = new TopicDto().setId(123L).setName("T1").setScore(3);

        // When:
        final Topic actual = dto.toTopic();

        // Then:
        assertThat(actual).usingRecursiveComparison().isEqualTo(new Topic().setId(123L).setName("T1").setScore(3));
    }

    @Test
    void should_create_from_model() {
        // Given:
        final Topic src = new Topic().setId(123L).setName("T1").setScore(3);

        // When:
        final TopicDto actual = TopicDto.from(src);

        // Then:
        assertThat(actual).usingRecursiveComparison().isEqualTo(new TopicDto().setId(123L).setName("T1").setScore(3));
    }
}