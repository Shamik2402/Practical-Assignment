package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.Status;
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
public class StatusControllerTest {
    MockMvc mockMvc;
    @InjectMocks
    StatusController controller;
    @Mock
    StoryService service;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void testGetAllStatuses() throws Exception {
        List<Status> list = Arrays.asList(Status.builder().statusId(1).statusName("To-Do").build());
        when(service.getAllStatuses()).thenReturn(list);
        mockMvc.perform(get("/statuses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].statusName").value(list.get(0).getStatusName()));

        verify(service,times(1)).getAllStatuses();
    }
}
