package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Team;
import com.project.practicalassignment.repository.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {
    @Mock
    private TeamRepository repository;
    @InjectMocks
    private TeamServiceImpl service;
    @Test
    public void testCreateTeam() {
        Team team = Team.builder()
                .id(1)
                .name("JIRA").build();

        when(repository.save(team)).thenReturn(team);

        Team createdTeam = service.createTeam(team);

        Assertions.assertThat(createdTeam.getId()).isEqualTo(team.getId());
        Assertions.assertThat(createdTeam.getName()).isEqualTo(team.getName());
    }
    @Test
    public void testGetAllTeams() {
        List<Team> teams = Arrays.asList(Team.builder().id(1).name("JIRA").build(),
                Team.builder().id(2).name("Bose").build());

        when(repository.findAll()).thenReturn(teams);

        List<Team> allTeams = service.getAllTeams();

        Assertions.assertThat(allTeams.size()).isEqualTo(teams.size());
        Assertions.assertThat(allTeams.size()).isEqualTo(2);
        Assertions.assertThat(allTeams.get(0).getName()).isEqualTo("JIRA");
        Assertions.assertThat(allTeams.get(0).getName()).isNotEqualTo("Bose");
    }
}
