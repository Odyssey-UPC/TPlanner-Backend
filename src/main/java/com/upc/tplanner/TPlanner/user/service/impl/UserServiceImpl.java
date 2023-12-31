package com.upc.tplanner.TPlanner.user.service.impl;

import com.upc.tplanner.TPlanner.user.model.User;
import com.upc.tplanner.TPlanner.user.repository.UserRepository;
import com.upc.tplanner.TPlanner.user.service.UserService;

import com.upc.tplanner.TPlanner.utils.exception.ResourceNotFoundException;
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

    @Override
    public Long getUserIdByUsername(String username) {
        return userRepository.findByUsername(username).get().getId();
    }

    @Override
    public User getUserById(Long id) {
        existsUserById(id);
        return userRepository.findById(id).get();
    }

    private void existsUserById(Long id) {
        if (!userRepository.existsById(id)){
            throw new ValidationException("user not found");
        }
    }

    private void existsUserByUsername(User user) {
        if (userRepository.existsUserByUsername(user.getUsername())){
            throw new ResourceNotFoundException("username already exists");
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
        if (user.getProfileLink() == null || user.getProfileLink().isEmpty()){
            throw new ValidationException("profile link is required");
        }
        if (user.getBirthdate() == null || user.getBirthdate().toString().isEmpty()){
            throw new ValidationException("birthdate is required");
        }
    }
}
