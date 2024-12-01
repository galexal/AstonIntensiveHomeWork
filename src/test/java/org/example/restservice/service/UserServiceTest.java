package org.example.restservice.service;

import org.example.restservice.model.User;
import org.example.restservice.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;

    @Before
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1);
        user1.setUserName("user1");

        User user2 = new User();
        user2.setId(2);
        user2.setUserName("user2");

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getUserName());
    }
}