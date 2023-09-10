package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.Priority;
import com.project.practicalassignment.entity.Status;
import com.project.practicalassignment.entity.Story;
import com.project.practicalassignment.entity.StoryType;
import com.project.practicalassignment.service.StoryServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = StoryController.class)
public class StoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private StoryController storyController;
    @MockBean
    private StoryServiceImpl storyService;

    private List<Story> storyList;

    @BeforeEach
    public void setUp() {

        storyList = new ArrayList<>();

        Status status = Status.builder()
                .statusName("Completed").build();

        Priority priority = Priority.builder()
                .priorityLevel("Low").build();

        StoryType storyType = StoryType.builder()
                .storyType("Enhancement").build();

        Story story = Story.builder()
                .storyTitle("This is a title")
                .storyDescription("This is a description")
                .storyStatus(status)
                .storyPriority(priority)
                .storyType(storyType)
                .createdDate(LocalDate.now())
                .build();

        storyList.add(story);
    }

    @Test
    public void fetchAllStories() throws Exception {

        given(storyService.getAllStories()).willReturn(storyList);

        this.mockMvc.perform(get("http://localhost:8080/stories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(storyList.size())));
    }
}
