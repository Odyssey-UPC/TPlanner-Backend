package com.upc.tplanner.TPlanner.touristService.service.impl;

import com.upc.tplanner.TPlanner.city.repository.CityRepository;
import com.upc.tplanner.TPlanner.touristService.model.TouristService;
import com.upc.tplanner.TPlanner.touristService.repository.TouristServiceRepository;
import com.upc.tplanner.TPlanner.touristService.service.TouristServiceService;
import com.upc.tplanner.TPlanner.user.repository.TouristProviderRepository;
import com.upc.tplanner.TPlanner.utils.exception.ResourceNotFoundException;
import com.upc.tplanner.TPlanner.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristServiceServiceImpl implements TouristServiceService {

    @Autowired
    TouristServiceRepository touristServiceRepository;

    @Autowired
    TouristProviderRepository touristProviderRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<TouristService> getAllTouristServices() {
        return touristServiceRepository.findAll();
    }

    @Override
    public TouristService getTouristServiceById(Long touristServiceId) {
        existsById(touristServiceId);
        return touristServiceRepository.findById(touristServiceId).get();
    }

    @Override
    public TouristService createTouristService(Long TouristProviderId, Long cityId, TouristService touristService) {
        existsTouristProviderById(TouristProviderId);
        existsCityById(cityId);
        validateTouristService(touristService);
        touristService.setTouristProvider(touristProviderRepository.findById(TouristProviderId).get());
        touristService.setCity(cityRepository.findById(cityId).get());
        return touristServiceRepository.save(touristService);
    }

    @Override
    public TouristService updateTouristService(Long touristServiceId, TouristService touristServiceDetails) {
        existsById(touristServiceId);
        validateTouristService(touristServiceDetails);
        TouristService touristService = touristServiceRepository.findById(touristServiceId).get();
        touristService.setServiceName(touristServiceDetails.getServiceName());
        touristService.setServiceDescription(touristServiceDetails.getServiceDescription());
        touristService.setServiceCategory(touristServiceDetails.getServiceCategory());
        touristService.setServiceDate(touristServiceDetails.getServiceDate());
        touristService.setServiceDeadline(touristServiceDetails.getServiceDeadline());
        touristService.setCapacity(touristServiceDetails.getCapacity());
        touristService.setContent(touristServiceDetails.getContent());
        touristService.setPrice(touristServiceDetails.getPrice());
        return touristServiceRepository.save(touristService);
    }

    private void existsById(Long touristServiceId) {
        if (!touristServiceRepository.existsById(touristServiceId)) {
            throw new ResourceNotFoundException("Tourist Service not found");
        }
    }

    private void validateTouristService(TouristService touristService) {
        if (touristService.getServiceName() == null || touristService.getServiceName().isEmpty()) {
            throw new ValidationException("Tourist Service name is required");
        }
        if (touristService.getServiceDescription() == null || touristService.getServiceDescription().isEmpty()) {
            throw new ValidationException("Tourist Service description is required");
        }
        if (touristService.getServiceCategory() == null || touristService.getServiceCategory().isEmpty()) {
            throw new ValidationException("Tourist Service category is required");
        }
        if (!touristService.getServiceCategory().equals("ACTIVITIES") && !touristService.getServiceCategory().equals("RESTAURANTS") && !touristService.getServiceCategory().equals("HOTELS") && !touristService.getServiceCategory().equals("TOURS")) {
            throw new ValidationException("Tourist Service category must be ACTIVITIES, RESTAURANTS, HOTELS or TOURS");
        }
        if (touristService.getServiceDate() == null) {
            throw new ValidationException("Tourist Service date is required");
        }
        if (touristService.getServiceDeadline() == null) {
            throw new ValidationException("Tourist Service deadline is required");
        }
        if (touristService.getServiceDate().isBefore(touristService.getServiceDeadline())) {
            throw new ValidationException("Tourist Service date must be before deadline");
        }
        if (touristService.getServiceDate().isBefore(java.time.LocalDateTime.now())) {
            throw new ValidationException("Tourist Service date must be after today");
        }
        if (touristService.getCapacity() == null) {
            throw new ValidationException("Tourist Service capacity is required");
        }
        if (touristService.getCapacity() < 0) {
            throw new ValidationException("Tourist Service capacity must be greater than 0");
        }
        if (touristService.getContent() == null || touristService.getContent().isEmpty()) {
            throw new ValidationException("Tourist Service content is required");
        }
        if (touristService.getPrice() == null) {
            throw new ValidationException("Tourist Service cost is required");
        }
        if (touristService.getPrice() <= 0) {
            throw new ValidationException("Tourist Service cost must be greater than 0");
        }
    }

    private void existsCityById(Long cityId) {
        if (!cityRepository.existsById(cityId)) {
            throw new ResourceNotFoundException("City with id " + cityId + " not found");
        }
    }

    private void existsTouristProviderById(Long touristProviderId) {
        if (!touristProviderRepository.existsById(touristProviderId)) {
            throw new ResourceNotFoundException("Tourist Provider with id " + touristProviderId + " not found");
        }
    }

}
