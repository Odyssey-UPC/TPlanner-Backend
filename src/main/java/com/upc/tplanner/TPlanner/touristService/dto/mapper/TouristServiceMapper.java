package com.upc.tplanner.TPlanner.touristService.dto.mapper;

import com.upc.tplanner.TPlanner.touristService.dto.TouristServiceRequest;
import com.upc.tplanner.TPlanner.touristService.dto.TouristServiceResponse;
import com.upc.tplanner.TPlanner.touristService.model.TouristService;
import com.upc.tplanner.TPlanner.user.dto.TouristProviderResponse;
import com.upc.tplanner.TPlanner.user.dto.mapper.TouristProviderMapper;
import com.upc.tplanner.TPlanner.user.model.TouristProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TouristServiceMapper {

    TouristServiceMapper INSTANCE = Mappers.getMapper(TouristServiceMapper.class);

    List<TouristServiceResponse> touristServicesToTouristServicesResponse(List<TouristService> touristServices);

    TouristService touristServiceRequestToTouristService(TouristServiceRequest touristServiceRequest);

    @Mapping(source = "touristProvider", target = "touristProviderResponse", qualifiedByName = "touristProviderToTouristProviderResponse")
    TouristServiceResponse touristServiceToTouristServiceResponse(TouristService touristService);

    @Named("touristProviderToTouristProviderResponse")
    public static TouristProviderResponse touristProviderToTouristProviderResponse(TouristProvider touristProvider){
        return TouristProviderMapper.INSTANCE.TouristProviderToTouristProviderResponse(touristProvider);
    }

}
