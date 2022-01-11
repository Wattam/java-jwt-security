package com.wattam.controller;

import java.util.List;

import com.wattam.error.RecordNotFoundException;
import com.wattam.model.UserModel;
import com.wattam.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public UserController(UserRepository userRepository, PasswordEncoder encoder) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/get")
    public List<UserModel> getAll() {

        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> get(@PathVariable Long id) {

        if (userRepository.findById(id).isEmpty()) {
            throw new RecordNotFoundException("no user with the ID: " + id);
        }
        return new ResponseEntity<UserModel>(userRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public UserModel post(@RequestBody UserModel user) {

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PutMapping("/put")
    public ResponseEntity<UserModel> put(@RequestBody UserModel user) {

        if (userRepository.findById(user.getId()).isEmpty()) {
            throw new RecordNotFoundException("no user with the ID: " + user.getId());
        }

        user.setPassword(encoder.encode(user.getPassword()));
        return new ResponseEntity<UserModel>(userRepository.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        if (userRepository.findById(id).isEmpty()) {
            throw new RecordNotFoundException("no user with the ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
