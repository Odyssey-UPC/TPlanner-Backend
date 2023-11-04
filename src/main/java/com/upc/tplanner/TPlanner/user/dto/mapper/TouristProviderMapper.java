package com.upc.tplanner.TPlanner.user.dto.mapper;

import com.upc.tplanner.TPlanner.user.dto.TouristProviderRequest;
import com.upc.tplanner.TPlanner.user.dto.TouristProviderResponse;

import com.upc.tplanner.TPlanner.user.dto.TouristResponse;
import com.upc.tplanner.TPlanner.user.dto.UserResponse;
import com.upc.tplanner.TPlanner.user.model.Tourist;
import com.upc.tplanner.TPlanner.user.model.TouristProvider;
import com.upc.tplanner.TPlanner.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TouristProviderMapper {
    TouristProviderMapper INSTANCE = Mappers.getMapper(TouristProviderMapper.class);
    User TouristProviderRequestToUser(TouristProviderRequest touristProviderRequest);

    List<TouristProviderResponse> TouristProvidersToTouristProvidersResponse(List<TouristProvider> touristProviders);
    TouristProvider TouristProviderRequestToTouristProvider(TouristProviderRequest touristProviderRequest);
    @Mapping(source = "user", target = "userResponse", qualifiedByName = "userToUserResponse")
    TouristProviderResponse TouristProviderToTouristProviderResponse(TouristProvider touristProvider);
    @Named("userToUserResponse")
    public static UserResponse userToUserResponse(User user){
        return UserMapper.INSTANCE.userToUserResponse(user);
    }

}
