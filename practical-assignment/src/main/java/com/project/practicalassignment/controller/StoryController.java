package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.Story;
import com.project.practicalassignment.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class StoryController {
    private final StoryService service;
    @GetMapping("/stories")
    public List<Story> getAllStory() {
        return this.service.getAllStories();
    }

    @DeleteMapping("/stories/{id}")
    public Story deleteStoryById(@PathVariable String id) {
        return this.service.deleteStoryById(Long.parseLong(id));
    }
    @PostMapping("/stories")
    public Story createNewStory(@RequestBody Story story) {
        return service.createNewStory(story);
    }
}
