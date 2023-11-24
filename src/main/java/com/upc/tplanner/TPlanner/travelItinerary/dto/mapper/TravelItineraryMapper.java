package com.upc.tplanner.TPlanner.travelItinerary.dto.mapper;

import com.upc.tplanner.TPlanner.touristService.dto.TouristServiceResponse;
import com.upc.tplanner.TPlanner.touristService.dto.mapper.TouristServiceMapper;
import com.upc.tplanner.TPlanner.touristService.model.TouristService;
import com.upc.tplanner.TPlanner.travelItinerary.dto.TravelItineraryRequest;
import com.upc.tplanner.TPlanner.travelItinerary.dto.TravelItineraryResponse;
import com.upc.tplanner.TPlanner.travelItinerary.model.TravelItinerary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TravelItineraryMapper {

    TravelItineraryMapper INSTANCE = Mappers.getMapper(TravelItineraryMapper.class);

    List<TravelItineraryResponse> travelItinerariesToTravelItineraries(List<TravelItinerary> travelItinerary);

    TravelItinerary travelItineraryRequestToTravelItinerary(TravelItineraryRequest travelItineraryRequest);

    @Mapping(source = "travelServices", target = "travelServices", qualifiedByName = "touristServicesToTouristServicesResponse")
    TravelItineraryResponse travelItineraryToTravelItineraryResponse(TravelItinerary travelItinerary);

    @Named("touristServicesToTouristServicesResponse")
    public static List<TouristServiceResponse> touristServicesToTouristServicesResponse(List<TouristService> touristService){
        return TouristServiceMapper.INSTANCE.touristServicesToTouristServicesResponse(touristService);
    }

}
