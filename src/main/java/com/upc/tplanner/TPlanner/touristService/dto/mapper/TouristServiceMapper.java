package com.upc.tplanner.TPlanner.touristService.dto.mapper;

import com.upc.tplanner.TPlanner.touristService.dto.TouristServiceRequest;
import com.upc.tplanner.TPlanner.touristService.dto.TouristServiceResponse;
import com.upc.tplanner.TPlanner.touristService.model.TouristService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TouristServiceMapper {

    TouristServiceMapper INSTANCE = Mappers.getMapper(TouristServiceMapper.class);

    List<TouristServiceResponse> touristServicesToTouristServicesResponse(List<TouristService> touristServices);

    TouristService touristServiceRequestToTouristService(TouristServiceRequest touristServiceRequest);

    TouristServiceResponse touristServiceToTouristServiceResponse(TouristService touristService);

}
