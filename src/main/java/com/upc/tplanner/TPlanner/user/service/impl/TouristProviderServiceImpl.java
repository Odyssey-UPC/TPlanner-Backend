package com.upc.tplanner.TPlanner.user.service.impl;

import com.upc.tplanner.TPlanner.user.model.TouristProvider;
import com.upc.tplanner.TPlanner.user.model.User;
import com.upc.tplanner.TPlanner.user.repository.TouristProviderRepository;
import com.upc.tplanner.TPlanner.user.service.TouristProviderService;
import com.upc.tplanner.TPlanner.user.service.UserService;
import com.upc.tplanner.TPlanner.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristProviderServiceImpl implements TouristProviderService {

    @Autowired
    TouristProviderRepository touristProviderRepository;

    @Autowired
    UserService userService;

    @Override
    public List<TouristProvider> getAllTouristProviders() {
        return touristProviderRepository.findAll();
    }

    @Override
    public TouristProvider createTouristProvider(User user, TouristProvider touristProvider) {
        validateTouristProvider(touristProvider);
        var userCreated = userService.createUser(user, "tourist provider");
        touristProvider.setUser(userCreated);
        return touristProviderRepository.save(touristProvider);
    }

    private void validateTouristProvider(TouristProvider touristProvider) {
        if (touristProvider.getOrganizationName() == null || touristProvider.getOrganizationName().isEmpty()){
            throw new ValidationException("organization name is required");
        }
        if (touristProvider.getOrganizationName().length() > 25){
            throw new ValidationException("organization name must not exceed 25 characters");
        }
        if (touristProvider.getOrganizationDescription() == null || touristProvider.getOrganizationDescription().isEmpty()){
            throw new ValidationException("organization description is required");
        }
        if (touristProvider.getOrganizationDescription().length() > 500){
            throw new ValidationException("organization description must not exceed 500 characters");
        }
        if (touristProvider.getWebsiteLink() == null || touristProvider.getWebsiteLink().isEmpty()){
            throw new ValidationException("website link is required");
        }
    }
}
