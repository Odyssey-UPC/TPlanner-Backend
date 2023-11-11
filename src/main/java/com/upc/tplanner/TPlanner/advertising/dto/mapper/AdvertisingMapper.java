package com.upc.tplanner.TPlanner.advertising.dto.mapper;

import com.upc.tplanner.TPlanner.advertising.dto.AdvertisingRequest;
import com.upc.tplanner.TPlanner.advertising.dto.AdvertisingResponse;
import com.upc.tplanner.TPlanner.advertising.model.Advertising;
import com.upc.tplanner.TPlanner.user.dto.TouristProviderResponse;
import com.upc.tplanner.TPlanner.user.dto.mapper.TouristProviderMapper;
import com.upc.tplanner.TPlanner.user.model.TouristProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdvertisingMapper {
    AdvertisingMapper INSTANCE = Mappers.getMapper(AdvertisingMapper.class);


    List<AdvertisingResponse> advertisementsResponseToAdvertisementsResponseResponse(List<Advertising> advertisings);

    Advertising advertisingRequestToAdvertising(AdvertisingRequest advertisingRequest);

    @Mapping(source = "touristProvider", target = "touristProviderResponse", qualifiedByName = "touristProviderToTouristProviderResponse")
    AdvertisingResponse advertisingToAdvertisingResponse(Advertising advertising);

    @Named("touristProviderToTouristProviderResponse")
    public static TouristProviderResponse touristProviderToTouristProviderResponse(TouristProvider touristProvider){
        return TouristProviderMapper.INSTANCE.TouristProviderToTouristProviderResponse(touristProvider);
    }

}
