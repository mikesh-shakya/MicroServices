package com.example.microServices.UserServices.services;

import com.example.microServices.UserServices.entities.User;

import java.util.List;

public interface UserServices {

    // create user
    User createUser(User user);

    //get All user
    List<User> getAllUsers();

    //get user by Id
    User getUser(String userId);

    //update user by id
    User updateUser(String userId, User user);

    // delete user by Id
    void deleteUser(String userId);
}
