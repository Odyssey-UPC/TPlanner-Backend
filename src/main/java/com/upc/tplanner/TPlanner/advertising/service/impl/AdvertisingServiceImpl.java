package com.upc.tplanner.TPlanner.advertising.service.impl;

import com.upc.tplanner.TPlanner.advertising.model.Advertising;
import com.upc.tplanner.TPlanner.advertising.repository.AdvertisingRepository;
import com.upc.tplanner.TPlanner.advertising.service.AdvertisingService;
import com.upc.tplanner.TPlanner.user.repository.TouristProviderRepository;
import com.upc.tplanner.TPlanner.utils.RandomSelection;
import com.upc.tplanner.TPlanner.utils.exception.ResourceNotFoundException;
import com.upc.tplanner.TPlanner.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdvertisingServiceImpl implements AdvertisingService {

    @Autowired
    AdvertisingRepository advertisingRepository;

    @Autowired
    TouristProviderRepository touristProviderRepository;

    @Override
    public List<Advertising> getAllAdvertisements() {
        return advertisingRepository.findAll();
    }

    @Override
    public Advertising getRandomAdvertising() {
        var advertisements = advertisingRepository.findAll();
        return RandomSelection.getRandomAdvertising(advertisements);
    }

    @Override
    public Advertising getAdvertisementById(Long id) {
        existAdvertisementById(id);
        return advertisingRepository.findById(id).get();
    }

    @Override
    public Advertising createAdvertisement(Long touristProviderId, Advertising advertising) {
        existTouristProviderById(touristProviderId);
        validateAdvertising(advertising);
        advertising.setViews(0L);
        advertising.setClicks(0L);
        advertising.setStartDate(LocalDateTime.now());
        advertising.setEndDate(LocalDateTime.now().plusDays(1));
        advertising.setStatus("active");
        advertising.setTouristProvider(touristProviderRepository.findById(touristProviderId).get());
        return advertisingRepository.save(advertising);
    }

    @Override
    public Advertising updateAdvertisement(Long id, Advertising advertisement) {
        existAdvertisementById(id);
        validateAdvertising(advertisement);
        var advertisingToUpdate = advertisingRepository.findById(id).get();
        validateAmountInvested(advertisingToUpdate, advertisement);
        advertisingToUpdate.setName(advertisement.getName());
        advertisingToUpdate.setThumbnail(advertisement.getThumbnail());
        advertisingToUpdate.setServiceName(advertisement.getServiceName());
        advertisingToUpdate.setRedirectTo(advertisement.getRedirectTo());
        advertisingToUpdate.setAmountInvested(advertisement.getAmountInvested());
        return advertisingRepository.save(advertisingToUpdate);
    }

    private void validateAmountInvested(Advertising advertisingToUpdate, Advertising advertisement) {
        if (advertisingToUpdate.getAmountInvested() > advertisement.getAmountInvested())
            throw new ValidationException("Amount invested cannot be less than the current amount invested");
    }

    @Override
    public void deleteAdvertisement(Long id) {

    }

    private void existAdvertisementById(Long id) {
        if (!advertisingRepository.existsById(id))
            throw new ResourceNotFoundException(String.format("Advertisement with id %d does not exist", id));
    }

    private void existTouristProviderById(Long touristProviderId) {
        if(!touristProviderRepository.existsById(touristProviderId))
            throw new ResourceNotFoundException(String.format("TouristProvider with id %d does not exist", touristProviderId));
    }

    private void validateAdvertising(Advertising advertising) {
        if (advertising.getName() == null || advertising.getName().isEmpty())
            throw new ValidationException("Name is required");
        if (advertising.getName().length() > 40)
            throw new ValidationException("Name must be less than 40 characters");
        if (advertising.getThumbnail() == null || advertising.getThumbnail().isEmpty())
            throw new ValidationException("Thumbnail is required");
        if (advertising.getServiceName() == null || advertising.getServiceName().isEmpty())
            throw new ValidationException("Service name is required");
        if (advertising.getServiceName().length() > 40)
            throw new ValidationException("Service name must be less than 40 characters");
        if (advertising.getRedirectTo() == null || advertising.getRedirectTo().isEmpty())
            throw new ValidationException("Redirect to is required");
        if (advertising.getAmountInvested() < 100)
            throw new ValidationException("Amount invested must be greater than 100");
    }



}
