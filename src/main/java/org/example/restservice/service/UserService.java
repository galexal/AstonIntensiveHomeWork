package org.example.restservice.service;

import org.example.restservice.model.Role;
import org.example.restservice.model.User;
import org.example.restservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser (User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void addRoleToUser (Long userId, Role role) {
        User user = userRepository.findById(userId);
        if (user != null) {
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }
}
