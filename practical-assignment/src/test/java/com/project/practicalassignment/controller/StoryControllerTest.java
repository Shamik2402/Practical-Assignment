package com.project.practicalassignment.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.practicalassignment.entity.*;
import com.project.practicalassignment.service.StoryService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class StoryControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private StoryController storyController;
    @Mock
    private StoryService storyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(storyController).build();
    }

    @Test
    void fetchAllStories() throws Exception {

        List<Story> storyList = new ArrayList<>();

        Status status = Status.builder()
                .statusId(3)
                .statusName("Completed").build();

        Priority priority = Priority.builder()
                .priorityId(3)
                .priorityLevel("Low").build();

        StoryType storyType = StoryType.builder()
                .storyTypeId(3)
                .storyType("Enhancement").build();

        Role role = Role.builder()
                .id(1)
                .role("Product Manager").build();

        Team team = Team.builder()
                .id(1)
                .name("Bose").build();

        User createdBy = User.builder()
                .id(1)
                .role(role)
                .reportsTo("Dravid")
                .username("Rohit")
                .password("rohit@123")
                .team(team).build();

        Story story = Story.builder()
                .storyId(1)
                .title("This is a title")
                .description("This is a description")
                .status(status)
                .priority(priority)
                .type(storyType)
                .createdDate(LocalDate.now())
                .createdBy(createdBy)
                .assignedTo(createdBy)
                .team(team)
                .build();

        storyList.add(story);

        when(storyService.getAllStories()).thenReturn(storyList);

        mockMvc.perform(get("/stories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(storyService, times(1)).getAllStories();
    }
    @Test
    public void testDeleteStoryById() throws Exception {

        Story story = Story.builder()
                        .storyId(1).title("This is Sample Story").build();

        when(storyService.deleteStoryById(1)).thenReturn(story);

        mockMvc.perform(delete("http://localhost:8080/stories/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(story.getTitle()))
                .andReturn();
        verify(storyService, times(1)).deleteStoryById(1);
    }
    @Test
    public void testCreateNewStory() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Story story = Story.builder()
                .storyId(1).title("This is Sample Story").build();

        when(storyService.createNewStory(story)).thenReturn(story);

        mockMvc.perform(post("/stories").content(mapper.writeValueAsString(story)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(story.getTitle()))
                .andExpect(jsonPath("$.description").value(story.getDescription()));

        verify(storyService, times(1)).createNewStory(story);
    }
    @Test
    public void testUpdateStory() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Story story = Story.builder()
                .storyId(1).title("This is Sample Story").build();

        when(storyService.updateStoryById(story,1)).thenReturn(story);
        mockMvc.perform(put("/stories/1").content(mapper.writeValueAsString(story)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(story.getTitle()));

        verify(storyService,times(1)).updateStoryById(story,1);
    }
    @Test
    public void testGetStoryById() throws Exception {
        Story story = Story.builder()
                .storyId(1).title("This is Sample Story").build();
        when(storyService.getStoryById(1)).thenReturn(story);
        mockMvc.perform(get("/stories/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(story.getTitle()))
                .andExpect(jsonPath("$.storyId").value(story.getStoryId()));

        verify(storyService,times(1)).getStoryById(1);
    }
    @Test
    public void testGetStoriesByTeam() throws Exception {
        List<Story> list = new ArrayList<>();
        var teamName = "Bose";
        Story story = Story.builder()
                .storyId(1).title("This is Sample Story").build();
        list.add(story);

        when(storyService.getStoriesByTeam(teamName)).thenReturn(list);
        mockMvc.perform(get("/stories/team").param("team", teamName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value(list.get(0).getTitle()));
        verify(storyService,times(1)).getStoriesByTeam(teamName);
    }
}
