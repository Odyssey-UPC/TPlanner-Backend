package com.upc.tplanner.TPlanner.touristService.controller;

import com.upc.tplanner.TPlanner.touristService.dto.TouristServiceRequest;
import com.upc.tplanner.TPlanner.touristService.dto.TouristServiceResponse;
import com.upc.tplanner.TPlanner.touristService.dto.mapper.TouristServiceMapper;
import com.upc.tplanner.TPlanner.touristService.service.TouristServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TouristServiceController {

    @Autowired
    TouristServiceService touristServiceService;

    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('TOURIST', 'ADMIN', 'PROVIDER')")
    @GetMapping("/touristServices")
    public ResponseEntity<List<TouristServiceResponse>> getAllTouristServices(){
        var touristServicesResponse = TouristServiceMapper.INSTANCE.touristServicesToTouristServicesResponse(touristServiceService.getAllTouristServices());
        return new ResponseEntity<>(touristServicesResponse, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('TOURIST', 'ADMIN', 'PROVIDER')")
    @GetMapping("/touristServices/{id}")
    public ResponseEntity<TouristServiceResponse> getTouristServiceById(@PathVariable("id") Long id){
        var touristServiceResponse = TouristServiceMapper.INSTANCE.touristServiceToTouristServiceResponse(touristServiceService.getTouristServiceById(id));
        return new ResponseEntity<>(touristServiceResponse, HttpStatus.OK);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('PROVIDER', 'ADMIN')")
    @PostMapping("/touristProviders/{touristProviderId}/cities/{cityId}/touristServices")
    public ResponseEntity<TouristServiceResponse> saveTouristService(@PathVariable("touristProviderId") Long touristProviderId, @PathVariable("cityId") Long cityId,
                                                                     @RequestBody TouristServiceRequest touristServiceRequest){
        var touristService = TouristServiceMapper.INSTANCE.touristServiceRequestToTouristService(touristServiceRequest);
        var touristServiceCreated = touristServiceService.createTouristService(touristProviderId, cityId, touristService);
        var touristServiceResponseCreated = TouristServiceMapper.INSTANCE.touristServiceToTouristServiceResponse(touristServiceCreated);
        return new ResponseEntity<>(touristServiceResponseCreated, HttpStatus.CREATED);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('PROVIDER', 'ADMIN')")
    @PutMapping("/touristServices/{id}")
    public ResponseEntity<TouristServiceResponse> updateTouristService(@PathVariable("id") Long id,
                                                                       @RequestBody TouristServiceRequest touristServiceRequest){
        var touristService = TouristServiceMapper.INSTANCE.touristServiceRequestToTouristService(touristServiceRequest);
        var touristServiceUpdated = touristServiceService.updateTouristService(id, touristService);
        var touristServiceResponseUpdated = TouristServiceMapper.INSTANCE.touristServiceToTouristServiceResponse(touristServiceUpdated);
        return new ResponseEntity<>(touristServiceResponseUpdated, HttpStatus.OK);
    }

}
