package com.example.eLearningFinal.services;

import com.example.eLearningFinal.model.User;
import com.example.eLearningFinal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getALLUser() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }


    public User getUserByusernameAndpassword(String username, String password) {
        User user =userRepository.findByusername(username);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
    public User getUserById(Long user_id) {
        Optional<User> userOptional = userRepository.findById(user_id);
        return  userOptional.orElse(null);
    }
}
