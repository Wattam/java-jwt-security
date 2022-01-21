package com.wattam.service.impl;

import java.util.List;
import java.util.Optional;

import com.wattam.model.UserModel;
import com.wattam.repository.UserRepository;
import com.wattam.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<UserModel> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> getUser(Long id) {

        return userRepository.findById(id);
    }

    @Override
    public UserModel addUser(UserModel user) {

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

}
