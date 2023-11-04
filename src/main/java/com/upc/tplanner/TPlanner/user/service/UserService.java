package com.upc.tplanner.TPlanner.user.service;

import com.upc.tplanner.TPlanner.user.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user, String role);
}
