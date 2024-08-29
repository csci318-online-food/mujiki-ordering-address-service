package com.csci318.microservice.address.Entities.Relations;

import com.csci318.microservice.address.Constants.HttpStatus.Roles;

import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Roles role;
}
