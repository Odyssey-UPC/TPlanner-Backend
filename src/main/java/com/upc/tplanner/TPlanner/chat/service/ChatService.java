package com.upc.tplanner.TPlanner.chat.service;

import com.upc.tplanner.TPlanner.chat.model.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> getAllChats();
    List<Chat> getChatsByTouristIdAndTouristProviderId(Long touristId, Long touristProviderId);
    Chat createChat(Long touristId, Long touristProviderId ,Chat chat);

}
