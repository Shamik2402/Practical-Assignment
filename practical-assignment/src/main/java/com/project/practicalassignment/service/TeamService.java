package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Team;

import java.util.List;

public interface TeamService {
    Team createTeam(Team team);

    List<Team> getAllTeams();
}
