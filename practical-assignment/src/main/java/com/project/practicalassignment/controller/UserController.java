package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.Team;
import com.project.practicalassignment.entity.User;
import com.project.practicalassignment.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl service;
    @PostMapping("/user")
    public User CreateUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("/user")
    public Map<Team, List<User>> getUsersByTeam() {
        return service.getUsersByTeam();
    }

    @GetMapping("user/username")
    public User getUserByUsername(@RequestParam String username) {
        return service.getUserByUsername(username);
    }
}
