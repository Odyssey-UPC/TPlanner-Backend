package com.upc.tplanner.TPlanner.city.dto.mapper;

import com.upc.tplanner.TPlanner.city.dto.CityRequest;
import com.upc.tplanner.TPlanner.city.dto.CityResponse;
import com.upc.tplanner.TPlanner.city.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    List<CityResponse> cityToCitiesResponse(List<City> cities);

    City cityRequestToCity(CityRequest cityRequest);
    CityResponse cityToCityResponse(City city);

}
