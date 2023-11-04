package com.upc.tplanner.TPlanner.user.service.impl;

import com.upc.tplanner.TPlanner.user.model.Tourist;
import com.upc.tplanner.TPlanner.user.model.User;
import com.upc.tplanner.TPlanner.user.repository.TouristRepository;
import com.upc.tplanner.TPlanner.user.service.TouristService;
import com.upc.tplanner.TPlanner.user.service.UserService;
import com.upc.tplanner.TPlanner.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristServiceImpl implements TouristService {

    @Autowired
    TouristRepository touristRepository;

    @Autowired
    UserService userService;

    @Override
    public List<Tourist> getAllTourists() {
        return touristRepository.findAll();
    }

    @Override
    public Tourist createTourist(User user, Tourist tourist) {
        validateTourist(tourist);
        var userCreated = userService.createUser(user, "tourist");
        tourist.setUser(userCreated);
        tourist.setHasPremium(false);
        tourist.setTravelPoints(0);
        return touristRepository.save(tourist);
    }

    private void validateTourist(Tourist tourist) {
        if (tourist.getFirstName() == null || tourist.getFirstName().isEmpty()){
            throw new ValidationException("first name is required");
        }
        if (tourist.getFirstName().length() > 30){
            throw new ValidationException("first name must not exceed 30 characters");
        }
        if (tourist.getLastName() == null || tourist.getLastName().isEmpty()){
            throw new ValidationException("last name is required");
        }
        if (tourist.getLastName().length() > 30){
            throw new ValidationException("last name must not exceed 30 characters");
        }
        if (tourist.getNationality() == null || tourist.getNationality().isEmpty()){
            throw new ValidationException("nationality is required");
        }
        if (tourist.getNationality().length() > 20){
            throw new ValidationException("nationality must not exceed 20 characters");
        }
        if (tourist.getGender() == null || tourist.getGender().isEmpty()){
            throw new ValidationException("gender is required");
        }
        if (!tourist.getGender().equals("F") && !tourist.getGender().equals("M")){
            throw new ValidationException("gender must be F (female) or M (male)");
        }
    }
}
