package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Role;
import com.project.practicalassignment.models.AuthenticationRequest;
import com.project.practicalassignment.repository.RoleRepository;
import com.project.practicalassignment.repository.UserRepository;
import com.project.practicalassignment.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final JwtUtil jwtTokenUtil;
    private final UserRepository repository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.project.practicalassignment.entity.User user = repository.findByUsername(username);
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }

    public String authenticate(AuthenticationRequest authenticationRequest) throws Exception {
        final UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());
        if(userDetails.getPassword().equals(authenticationRequest.getPassword())) {
            return jwtTokenUtil.generateToken(userDetails);
        }
        throw new Exception("Incorrect Password");
    }

    public com.project.practicalassignment.entity.User SaveUser(com.project.practicalassignment.entity.User user) {
        int roleId = user.getRole().getId();
        Role role = roleRepository.findById(roleId).get();
        user.setRole(role);
        return repository.save(user);
    }

}
