package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Team;
import com.project.practicalassignment.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;
    @Override
    public Team createTeam(Team team) {
        return repository.save(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return repository.findAll();
    }
}
