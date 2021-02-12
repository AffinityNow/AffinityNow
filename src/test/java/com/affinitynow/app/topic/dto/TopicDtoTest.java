package com.affinitynow.app.topic.dto;

import com.affinitynow.app.model.Topic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TopicDtoTest {

    @Test
    void testDoitConvertirLeDomaineEnDTO() {
        // Given:
        final Topic src = new Topic().setId(123L).setName("MyTopic");
        final TopicDto expected = new TopicDto().setId(123L).setName("MyTopic");

        // When:
        final TopicDto actual = TopicDto.from(src);

        // Then:
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }


}