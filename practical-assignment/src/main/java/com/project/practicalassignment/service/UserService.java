package com.project.practicalassignment.service;

import com.project.practicalassignment.entity.Team;
import com.project.practicalassignment.entity.User;
import com.project.practicalassignment.models.AuthenticationRequest;

import java.util.List;
import java.util.Map;

public interface UserService {

    String authenticate(AuthenticationRequest request) throws Exception;

    User saveUser(User user);

    Map<Team, List<User>> getUsersByTeam();

    User getUserByUsername(String username);
}
