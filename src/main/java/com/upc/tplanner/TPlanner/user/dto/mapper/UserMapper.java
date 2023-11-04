package com.upc.tplanner.TPlanner.user.dto.mapper;

import com.upc.tplanner.TPlanner.user.dto.UserRequest;
import com.upc.tplanner.TPlanner.user.dto.UserResponse;
import com.upc.tplanner.TPlanner.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserResponse(User user);

    User userRequestToUser(UserRequest userRequest);
    List<UserResponse> userListToUserResponseList(List<User> users);
}
