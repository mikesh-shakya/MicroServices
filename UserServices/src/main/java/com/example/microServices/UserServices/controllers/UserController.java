package com.example.microServices.UserServices.controllers;

import com.example.microServices.UserServices.entities.User;
import com.example.microServices.UserServices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    //create a user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userServices.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    //get All users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userServices.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        User user = userServices.getUser(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    // update user by id
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user){
        User updatedUser = userServices.updateUser(userId,user);
        return ResponseEntity.ok(updatedUser);
    }

    // delete user by id
    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable String userId){
        userServices.deleteUser(userId);
    }
}
