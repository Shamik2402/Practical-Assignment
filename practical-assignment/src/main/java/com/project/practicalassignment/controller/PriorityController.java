package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.Priority;
import com.project.practicalassignment.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PriorityController {

    private final StoryService service;
    @GetMapping("/priorities")
    public List<Priority> getAllPriorities() {
        return service.getAllPriorities();
    }
}
