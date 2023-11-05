package com.upc.tplanner.TPlanner.user.controller;

import com.upc.tplanner.TPlanner.user.dto.UserRequest;
import com.upc.tplanner.TPlanner.user.dto.UserResponse;
import com.upc.tplanner.TPlanner.user.dto.mapper.UserMapper;
import com.upc.tplanner.TPlanner.user.model.User;
import com.upc.tplanner.TPlanner.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        var usersResponse = UserMapper.INSTANCE.userListToUserResponseList(userService.getAllUsers());
        return new ResponseEntity<List<UserResponse>>(usersResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        var user = UserMapper.INSTANCE.userRequestToUser(userRequest);
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.createUser(user, "-"));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

}
