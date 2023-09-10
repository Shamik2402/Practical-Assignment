package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.Story;
import com.project.practicalassignment.service.StoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoryController {
    @Autowired
    private StoryServiceImpl storyService;
    @GetMapping("/stories")
    public List<Story> getAllStory() {
        return this.storyService.getAllStories();
    }
}
