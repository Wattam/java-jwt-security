package com.wattam.service;

import java.util.List;
import java.util.Optional;

import com.wattam.model.UserModel;

public interface UserService {

    public List<UserModel> getAllUsers();

    public Optional<UserModel> getUser(Long id);

    public UserModel addUser(UserModel user);

    public void deleteUser(Long id);
}
