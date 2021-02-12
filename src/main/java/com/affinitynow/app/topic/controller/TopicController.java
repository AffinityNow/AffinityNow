package com.affinitynow.app.topic.controller;

import com.affinitynow.app.topic.dto.TopicDto;
import com.affinitynow.app.topic.repository.TopicRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@CrossOrigin
@RequestMapping("/topic")
public class TopicController {

    private final TopicRepository topicRepository;

    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping
    public List<TopicDto> getTopics() {
        return topicRepository.findAll().stream().map(TopicDto::from).collect(Collectors.toList());
    }
}
