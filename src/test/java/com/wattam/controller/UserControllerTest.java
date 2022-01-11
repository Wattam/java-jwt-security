package com.wattam.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import com.wattam.error.RecordNotFoundException;
import com.wattam.model.UserModel;
import com.wattam.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@DataJpaTest
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController(userRepository, encoder);
    }

    @Test
    void testGetAll() {

        // Given
        UserModel user1 = new UserModel();
        user1.setUsername("username1");
        user1.setPassword("password");
        userRepository.save(user1);

        UserModel user2 = new UserModel();
        user2.setUsername("username2");
        user2.setPassword("password");
        userRepository.save(user2);

        // When
        List<UserModel> expected = userRepository.findAll();
        List<UserModel> actual = userController.getAll();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void testGet() {

        // Given
        UserModel user = new UserModel();
        user.setUsername("username");
        user.setPassword("password");
        userRepository.save(user);

        // When
        UserModel expected = userRepository.findById(user.getId()).get();
        UserModel actual = userController.get(user.getId()).getBody();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void testGetException() {

        // When
        RecordNotFoundException thrown = assertThrows(
                RecordNotFoundException.class,
                () -> {
                    userController.get(1L);
                });

        // Then
        assertEquals("no user with the ID: 1", thrown.getMessage());
    }

    @Test
    void testPost() {

        // Given
        UserModel user = new UserModel();
        user.setUsername("username");
        user.setPassword("password");

        // When
        UserModel actual = userController.post(user);
        UserModel expected = userRepository.findById(user.getId()).get();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void testPut() {

        // Given
        UserModel user1 = new UserModel();
        user1.setUsername("username");
        user1.setPassword("password");
        user1.setPassword(encoder.encode(user1.getPassword()));
        userRepository.save(user1);

        UserModel user2 = new UserModel();
        user2.setId(user1.getId());
        user2.setUsername("usernameUpdated");
        user2.setPassword("passwordUpdated");

        // When
        UserModel expected = userController.put(user2).getBody();
        user2 = userRepository.findById(user2.getId()).get();

        // Then
        assertEquals(expected, user2);
    }

    @Test
    void testPutException() {

        
    }

    @Test
    void testDelete() {

    }
}
