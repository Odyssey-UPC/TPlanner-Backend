package com.upc.tplanner.TPlanner.advertising.controller;

import com.upc.tplanner.TPlanner.advertising.dto.AdvertisingRequest;
import com.upc.tplanner.TPlanner.advertising.dto.AdvertisingResponse;
import com.upc.tplanner.TPlanner.advertising.dto.mapper.AdvertisingMapper;
import com.upc.tplanner.TPlanner.advertising.service.AdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class AdvertisingController {

    @Autowired
    AdvertisingService advertisingService;

    @Transactional(readOnly = true)
    @GetMapping("/advertisements/selectRandom")
    public ResponseEntity<AdvertisingResponse> selectRandomAdvertisement() {
        var advertisement = advertisingService.getRandomAdvertising();
        return new ResponseEntity<>(AdvertisingMapper.INSTANCE.advertisingToAdvertisingResponse(advertisement), HttpStatus.OK);
    }


    @Transactional(readOnly = true)
    @GetMapping("/advertisements")
    public ResponseEntity<List<AdvertisingResponse>> getAllAdvertisements() {
        var advertisementsResponse = AdvertisingMapper.INSTANCE.advertisementsResponseToAdvertisementsResponseResponse(advertisingService.getAllAdvertisements());
        return new ResponseEntity<>(advertisementsResponse, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("advertisements/{id}")
    public ResponseEntity<AdvertisingResponse> getAdvertisementById(@PathVariable(value = "id") Long id) {
        var advertisement = advertisingService.getAdvertisementById(id);
        return new ResponseEntity<>(AdvertisingMapper.INSTANCE.advertisingToAdvertisingResponse(advertisement), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/touristProviders/{id}/advertisements")
    public ResponseEntity<AdvertisingResponse> createAdvertisement(@PathVariable(value = "id") Long touristProviderId,
                                                                   @RequestBody AdvertisingRequest advertisingRequest) {
        var advertising = AdvertisingMapper.INSTANCE.advertisingRequestToAdvertising(advertisingRequest);
        var advertisingCreated = advertisingService.createAdvertisement(touristProviderId, advertising);
        return new ResponseEntity<>(AdvertisingMapper.INSTANCE.advertisingToAdvertisingResponse(advertisingCreated), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/advertisements/{id}")
    public ResponseEntity<AdvertisingResponse> updateAdvertisement(@PathVariable(value = "id") Long id,
                                                                   @RequestBody AdvertisingRequest advertisingRequest) {
        var advertising = AdvertisingMapper.INSTANCE.advertisingRequestToAdvertising(advertisingRequest);
        var advertisingUpdated = advertisingService.updateAdvertisement(id, advertising);
        return new ResponseEntity<>(AdvertisingMapper.INSTANCE.advertisingToAdvertisingResponse(advertisingUpdated), HttpStatus.OK);
    }


}
