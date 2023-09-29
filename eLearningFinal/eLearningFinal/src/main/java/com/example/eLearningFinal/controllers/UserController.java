package com.example.eLearningFinal.controllers;

import com.example.eLearningFinal.model.Course;
import com.example.eLearningFinal.model.User;
import com.example.eLearningFinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping
    public List<User> getALLUser(){
        return userService.getALLUser();
    }
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @PostMapping("/login")
    public User getUserByUserName(@RequestBody User user) {
        return userService.getUserByusernameAndpassword(user.getUsername(), user.getPassword());
    }
    @GetMapping("/{user_id}")
    public User getUserById(@PathVariable Long user_id){
        return userService.getUserById(user_id);
    }
}
