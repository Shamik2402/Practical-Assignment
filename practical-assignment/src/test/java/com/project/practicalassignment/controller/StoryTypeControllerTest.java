package com.project.practicalassignment.controller;
import com.project.practicalassignment.entity.StoryType;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class StoryTypeControllerTest {
    MockMvc mockMvc;
    @InjectMocks
    StoryTypeController controller;
    @Mock
    StoryService service;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void testGetAllTypes() throws Exception {
        List<StoryType> list = Arrays.asList(StoryType.builder().storyTypeId(1).storyType("Bug").build());
        when(service.getAllTypes()).thenReturn(list);
        mockMvc.perform(get("/types"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].storyType").value(list.get(0).getStoryType()));

        verify(service,times(1)).getAllTypes();
    }
}
