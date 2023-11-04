package com.upc.tplanner.TPlanner.user.service.impl;

import com.upc.tplanner.TPlanner.user.model.User;
import com.upc.tplanner.TPlanner.user.repository.UserRepository;
import com.upc.tplanner.TPlanner.user.service.UserService;

import com.upc.tplanner.TPlanner.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user, String role) {
        existsUserByUsername(user);
        validateUser(user);
        user.setRole(role);
        user.setDateCreated(LocalDateTime.now());
        return userRepository.save(user);
    }

    private void existsUserByUsername(User user) {
        if (userRepository.existsUserByUsername(user.getUsername())){
            throw new ValidationException("username already exists");
        }
    }

    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()){
            throw new ValidationException("username is required");
        }
        if (user.getUsername().length() > 30){
            throw new ValidationException("username must not exceed 30 characters");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()){
            throw new ValidationException("password is required");
        }
        if (user.getPassword().length() > 30){
            throw new ValidationException("password must not exceed 30 characters");
        }
        if (user.getProfileLink() == null || user.getProfileLink().isEmpty()){
            throw new ValidationException("profile link is required");
        }
        if (user.getBirthdate() == null || user.getBirthdate().toString().isEmpty()){
            throw new ValidationException("birthdate is required");
        }
    }
}
