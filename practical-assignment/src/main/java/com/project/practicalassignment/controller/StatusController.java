package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.Status;
import com.project.practicalassignment.service.StoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StatusController {

    private StoryService service;

    @GetMapping("/statuses")
    public List<Status> getAllStatuses() {
        return this.service.getAllStatuses();
    }
}
