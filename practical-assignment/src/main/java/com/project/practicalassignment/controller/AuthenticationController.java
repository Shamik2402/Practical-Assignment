package com.project.practicalassignment.controller;

import com.project.practicalassignment.models.AuthenticationRequest;
import com.project.practicalassignment.models.AuthenticationResponse;
import com.project.practicalassignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(new AuthenticationResponse(userDetailsService.authenticate(authenticationRequest)));
    }

}
