package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.Story;
import com.project.practicalassignment.entity.Team;
import com.project.practicalassignment.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StoryController {
    @Autowired
    StoryService service;

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

    @PutMapping("/stories/{id}")
    public Story updateStory(@RequestBody Story story, @PathVariable String id) {
        return service.updateStoryById(story, Long.parseLong(id));
    }
    @GetMapping("/stories/{id}")
    public Story getStoryById(@PathVariable String id) {
        return this.service.getStoryById(Long.parseLong(id));
    }

    @GetMapping("/stories/team")
    public List<Story> getStoriesByTeam(@RequestParam String team) {
        return service.getStoriesByTeam(team);
    }

}
