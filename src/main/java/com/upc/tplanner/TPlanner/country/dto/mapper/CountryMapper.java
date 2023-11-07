package com.upc.tplanner.TPlanner.country.dto.mapper;

import com.upc.tplanner.TPlanner.country.dto.CountryRequest;
import com.upc.tplanner.TPlanner.country.dto.CountryResponse;
import com.upc.tplanner.TPlanner.country.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    List<CountryResponse> countriesToCountriesResponse(List<Country> countries);
    Country countryRequestToCountry(CountryRequest countryRequest);
    CountryResponse countryToCountryResponse(Country country);
}
