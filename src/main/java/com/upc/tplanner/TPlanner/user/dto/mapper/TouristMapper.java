package com.upc.tplanner.TPlanner.user.dto.mapper;

import com.upc.tplanner.TPlanner.user.dto.TouristRequest;
import com.upc.tplanner.TPlanner.user.dto.TouristResponse;
import com.upc.tplanner.TPlanner.user.dto.UserResponse;
import com.upc.tplanner.TPlanner.user.model.Tourist;
import com.upc.tplanner.TPlanner.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TouristMapper {

    TouristMapper INSTANCE = Mappers.getMapper(TouristMapper.class);

    User TouristRequestToUser(TouristRequest touristRequest);
    Tourist TouristRequestToTourist(TouristRequest touristRequest);
    List<TouristResponse> TouristsToTouristsResponse(List<Tourist> tourists);

    @Mapping(source = "user", target = "userResponse", qualifiedByName = "userToUserResponse")
    TouristResponse TouristToTouristResponse(Tourist tourist);

    @Named("userToUserResponse")
    public static UserResponse userToUserResponse(User user){
        return UserMapper.INSTANCE.userToUserResponse(user);
    }
}
