package com.upc.tplanner.TPlanner.chat.repository;

import com.upc.tplanner.TPlanner.chat.model.Chat;
import com.upc.tplanner.TPlanner.chat.model.ChatId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, ChatId>{

    List<Chat> getChatsById_Tourist_IdAndId_TouristProvider_Id(Long touristId, Long touristProviderId);

}
