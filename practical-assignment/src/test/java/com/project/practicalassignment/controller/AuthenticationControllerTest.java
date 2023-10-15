package com.project.practicalassignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.practicalassignment.models.AuthenticationRequest;
import com.project.practicalassignment.service.UserService;
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

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private AuthenticationController controller;
    @Mock
    private UserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testCreateAuthenticationToken() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String username = "Rohit";
        String password = "rohit@123";
        String token = "jwt";
        AuthenticationRequest request = AuthenticationRequest.builder()
                .username(username)
                .password(password).build();
        when(service.authenticate(request)).thenReturn(token);
        mockMvc.perform(post("/authenticate").content(mapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jwt").value(token));
        verify(service,times(1)).authenticate(request);
    }
}
