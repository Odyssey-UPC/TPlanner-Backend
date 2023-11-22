package com.upc.tplanner.TPlanner.touristService.service;

import com.upc.tplanner.TPlanner.touristService.model.TouristService;
import com.upc.tplanner.TPlanner.user.model.Tourist;

import java.util.List;

public interface TouristServiceService {

    List<TouristService> getAllTouristServices();
    TouristService getTouristServiceById(Long touristServiceId);
    TouristService createTouristService(Long TouristProviderId, Long cityId, TouristService touristService);
    TouristService updateTouristService(Long touristServiceId, TouristService touristServiceDetails);
}
