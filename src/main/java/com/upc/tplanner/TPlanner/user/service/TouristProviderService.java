package com.upc.tplanner.TPlanner.user.service;

import com.upc.tplanner.TPlanner.user.model.TouristProvider;
import com.upc.tplanner.TPlanner.user.model.User;

import java.util.List;

public interface TouristProviderService {
    List<TouristProvider> getAllTouristProviders();
    TouristProvider createTouristProvider(User user, TouristProvider touristProvider);
}
