package com.project.practicalassignment.controller;

import com.project.practicalassignment.entity.User;
import com.project.practicalassignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    @PostMapping("/user")
    public User CreateUser(@RequestBody User user) {
        return service.SaveUser(user);
    }

    @GetMapping("/user")
    public Map<String, List<User>> getUsersByManager() {
        return service.getUsersByManager();
    }
}
