package com.upc.tplanner.TPlanner.chat.dto.mapper;

import com.upc.tplanner.TPlanner.chat.dto.ChatRequest;
import com.upc.tplanner.TPlanner.chat.dto.ChatResponse;
import com.upc.tplanner.TPlanner.chat.model.Chat;
import com.upc.tplanner.TPlanner.user.dto.TouristProviderResponse;
import com.upc.tplanner.TPlanner.user.dto.TouristResponse;
import com.upc.tplanner.TPlanner.user.dto.mapper.TouristMapper;
import com.upc.tplanner.TPlanner.user.dto.mapper.TouristProviderMapper;
import com.upc.tplanner.TPlanner.user.model.Tourist;
import com.upc.tplanner.TPlanner.user.model.TouristProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    Chat chatRequestToChat(ChatRequest chatRequest);

    List<ChatResponse> chatsToChatsResponse(List<Chat> chats);

    @Mapping(source = "id.tourist", target = "touristResponse", qualifiedByName = "touristToTouristResponse")
    @Mapping(source = "id.touristProvider", target = "touristProviderResponse", qualifiedByName = "touristProviderToTouristProviderResponse")
    ChatResponse chatToChatResponse(Chat chat);

    @Named("touristToTouristResponse")
    public static TouristResponse touristToTouristResponse(Tourist tourist){
        return TouristMapper.INSTANCE.TouristToTouristResponse(tourist);
    }
    @Named("touristProviderToTouristProviderResponse")
    public static TouristProviderResponse touristProviderToTouristProviderResponse(TouristProvider touristProvider){
        return TouristProviderMapper.INSTANCE.TouristProviderToTouristProviderResponse(touristProvider);
    }
    
}
