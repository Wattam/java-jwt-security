package com.wattam.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.wattam.model.UserModel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUsername() {

        // Given
        UserModel user = new UserModel();
        user.setUsername("username");
        user.setPassword("password");
        userRepository.save(user);

        // When
        UserModel expected = userRepository.findByUsername(user.getUsername());

        // Then
        assertEquals(expected, user);
    }
}
