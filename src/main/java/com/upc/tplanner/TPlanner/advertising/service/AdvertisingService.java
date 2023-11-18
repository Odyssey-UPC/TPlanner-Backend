package com.upc.tplanner.TPlanner.advertising.service;

import com.upc.tplanner.TPlanner.advertising.model.Advertising;

import java.util.List;

public interface AdvertisingService {
    List<Advertising> getAllAdvertisements();
    Advertising getRandomAdvertising();
    Advertising getAdvertisementById(Long id);
    Advertising createAdvertisement(Long touristProviderId, Advertising advertisement);
    Advertising updateAdvertisement(Long id, Advertising advertisement);
    void deleteAdvertisement(Long id);
}
