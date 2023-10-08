package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Role;
import com.project.practicalassignment.entity.Team;
import com.project.practicalassignment.models.AuthenticationRequest;
import com.project.practicalassignment.repository.RoleRepository;
import com.project.practicalassignment.repository.TeamRepository;
import com.project.practicalassignment.repository.UserRepository;
import com.project.practicalassignment.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final JwtUtil jwtTokenUtil;
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final TeamRepository teamRepository;

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
        int teamId = user.getTeam().getId();
        Role role = roleRepository.findById(roleId).get();
        Team team = teamRepository.findById(teamId).get();
        user.setRole(role);
        System.out.println(team);
        user.setTeam(team);
        return repository.save(user);
    }

    public Map<String, List<com.project.practicalassignment.entity.User>> getUsersByManager() {
        return repository.findAll().stream().collect(Collectors.groupingBy(com.project.practicalassignment.entity.User::getReportsTo));
    }

}
