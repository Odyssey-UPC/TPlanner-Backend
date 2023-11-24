package com.upc.tplanner.TPlanner.operation.dto.mapper;

import com.upc.tplanner.TPlanner.operation.dto.OperationRequest;
import com.upc.tplanner.TPlanner.operation.dto.OperationResponse;
import com.upc.tplanner.TPlanner.operation.model.Operation;
import com.upc.tplanner.TPlanner.travelItinerary.dto.TravelItineraryResponse;
import com.upc.tplanner.TPlanner.travelItinerary.dto.mapper.TravelItineraryMapper;
import com.upc.tplanner.TPlanner.travelItinerary.model.TravelItinerary;
import com.upc.tplanner.TPlanner.user.dto.TouristResponse;
import com.upc.tplanner.TPlanner.user.dto.mapper.TouristMapper;
import com.upc.tplanner.TPlanner.user.model.Tourist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OperationMapper {

    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);
    List<OperationResponse> operationsToOperationResponses(List<Operation> operations);

    Operation operationRequestToOperation(OperationRequest operationRequest);

    @Mapping(source = "travelItinerary", target = "travelItinerary", qualifiedByName = "travelItineraryToTravelItineraryResponse")
    @Mapping(source = "tourist", target = "tourist", qualifiedByName = "touristToTouristResponse")
    OperationResponse operationToOperationResponse(Operation operation);

    @Named("travelItineraryToTravelItineraryResponse")
    public static TravelItineraryResponse travelItineraryToTravelItineraryResponse(TravelItinerary travelItinerary){
        return TravelItineraryMapper.INSTANCE.travelItineraryToTravelItineraryResponse(travelItinerary);
    }

    @Named("touristToTouristResponse")
    public static TouristResponse touristToTouristResponse(Tourist tourist){
        return TouristMapper.INSTANCE.TouristToTouristResponse(tourist);
    }

}
