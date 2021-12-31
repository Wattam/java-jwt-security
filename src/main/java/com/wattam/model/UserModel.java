package com.wattam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "username can't be empty")
    private String username;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "email can't be empty")
    @Email
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "password can't be empty")
    private String password;
}
