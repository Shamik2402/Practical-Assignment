package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.Team;
import com.project.practicalassignment.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService service;

    @PostMapping("/team")
    public Team createTeam(@RequestBody Team team) {
        return service.createTeam(team);
    }

    @GetMapping("/team")
    public List<Team> getAllTeams(){
        return service.getAllTeams();
    }
}
