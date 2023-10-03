package com.project.practicalassignment.service;

import com.project.practicalassignment.models.AuthenticationRequest;
import com.project.practicalassignment.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.security.auth.login.CredentialException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final JwtUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("user","password",new ArrayList<>());
    }

    public String authenticate(AuthenticationRequest authenticationRequest) throws Exception {
        final UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());
        if(userDetails.getPassword().equals(authenticationRequest.getPassword())) {
            return jwtTokenUtil.generateToken(userDetails);
        }
        throw new Exception("Incorrect Password");
    }

}
