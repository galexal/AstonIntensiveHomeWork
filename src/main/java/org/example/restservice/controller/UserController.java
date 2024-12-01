package org.example.restservice.controller;

import org.example.restservice.model.Role;
import org.example.restservice.model.User;
import org.example.restservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser (@RequestBody User user) {
        userService.createUser (user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/{userId}/roles")
    public void addRoleToUser (@PathVariable Long userId, @RequestBody Role role) {
        userService.addRoleToUser (userId, role);
    }
}