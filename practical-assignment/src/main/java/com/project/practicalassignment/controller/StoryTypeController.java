package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.StoryType;
import com.project.practicalassignment.service.StoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StoryTypeController {

    private StoryService service;

    @GetMapping("/types")
    public List<StoryType> getAllTypes() {
        return service.getAllTypes();
    }
}
