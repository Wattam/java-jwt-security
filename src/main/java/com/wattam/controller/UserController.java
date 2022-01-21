package com.wattam.controller;

import java.util.List;

import javax.validation.Valid;

import com.wattam.controller.exception.RecordNotFoundException;
import com.wattam.model.UserModel;
import com.wattam.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private UserService userService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<UserModel> getAll() {

        List<UserModel> users = userService.getAllUsers();
        if (users == null || users.isEmpty()) {
            throw new RecordNotFoundException("no users found");
        }
        return users;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserModel get(@PathVariable Long id) {

        return userService
                .getUser(id)
                .orElseThrow(() -> new RecordNotFoundException("no user with the ID: " + id));
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel post(@Valid @RequestBody UserModel user) {

        return userService.addUser(user);
    }

    @PutMapping("/put")
    @ResponseStatus(HttpStatus.OK)
    public UserModel put(@Valid @RequestBody UserModel user) {

        if (userService.getUser(user.getId()).isEmpty()) {
            throw new RecordNotFoundException("no user with the ID: " + user.getId());
        }

        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        if (userService.getUser(id).isEmpty()) {
            throw new RecordNotFoundException("no user with the ID: " + id);
        }
        userService.deleteUser(id);
    }
}
