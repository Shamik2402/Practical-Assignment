package com.project.practicalassignment.controller;
import com.project.practicalassignment.entity.Priority;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class PriorityControllerTest {
    MockMvc mockMvc;
    @InjectMocks
    PriorityController controller;
    @Mock
    StoryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void testGetAllPriorities() throws Exception {
        List<Priority> list = Arrays.asList(Priority.builder().priorityId(1).priorityLevel("High").build());
        when(service.getAllPriorities()).thenReturn(list);
        mockMvc.perform(get("/priorities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].priorityLevel").value(list.get(0).getPriorityLevel()));

        verify(service,times(1)).getAllPriorities();
    }
}
