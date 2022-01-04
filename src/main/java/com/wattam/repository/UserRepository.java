package com.wattam.repository;

import com.wattam.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    public UserModel findByUsername(String username);
}
