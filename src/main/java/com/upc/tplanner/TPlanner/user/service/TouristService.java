package com.upc.tplanner.TPlanner.user.service;

import com.upc.tplanner.TPlanner.user.model.Tourist;
import com.upc.tplanner.TPlanner.user.model.User;

import java.util.List;

public interface TouristService {

    List<Tourist> getAllTourists();
    Tourist createTourist(User user, Tourist tourist);
}
