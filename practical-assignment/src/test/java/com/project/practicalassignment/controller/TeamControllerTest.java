package com.project.practicalassignment.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.practicalassignment.entity.StoryType;
import com.project.practicalassignment.entity.Team;
import com.project.practicalassignment.service.StoryService;
import com.project.practicalassignment.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {
    MockMvc mockMvc;
    @InjectMocks
    TeamController controller;
    @Mock
    TeamService service;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void testCreateTeam() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Team team = Team.builder()
                .id(1)
                .name("JIRA").build();

        when(service.createTeam(team)).thenReturn(team);
        mockMvc.perform(post("/team").content(mapper.writeValueAsString(team)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(team.getName()));

        verify(service,times(1)).createTeam(team);
    }

    @Test
    public void testGetTeams() throws Exception {
        List<Team> list = new ArrayList<>();
        Team team = Team.builder()
                .id(1)
                .name("JIRA").build();
        list.add(team);
        when(service.getAllTeams()).thenReturn(list);
        mockMvc.perform(get("/team"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(list.get(0).getName()));

        verify(service,times(1)).getAllTeams();
    }
}
