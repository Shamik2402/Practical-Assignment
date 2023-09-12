package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.Story;
import com.project.practicalassignment.service.StoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class StoryController {
    private final StoryServiceImpl service;
    @GetMapping("/stories")
    public List<Story> getAllStory() {
        return this.service.getAllStories();
    }
}
