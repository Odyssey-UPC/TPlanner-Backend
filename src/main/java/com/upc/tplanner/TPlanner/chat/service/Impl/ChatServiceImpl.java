package com.upc.tplanner.TPlanner.chat.service.Impl;

import com.upc.tplanner.TPlanner.chat.model.Chat;
import com.upc.tplanner.TPlanner.chat.model.ChatId;
import com.upc.tplanner.TPlanner.chat.repository.ChatRepository;
import com.upc.tplanner.TPlanner.chat.service.ChatService;
import com.upc.tplanner.TPlanner.user.repository.TouristProviderRepository;
import com.upc.tplanner.TPlanner.user.repository.TouristRepository;
import com.upc.tplanner.TPlanner.utils.exception.ResourceNotFoundException;
import com.upc.tplanner.TPlanner.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    TouristRepository touristRepository;
    @Autowired
    TouristProviderRepository touristProviderRepository;

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public List<Chat> getChatsByTouristIdAndTouristProviderId(Long touristId, Long touristProviderId) {
        existTouristById(touristId);
        existTouristProviderById(touristProviderId);
        return chatRepository.getChatsById_Tourist_IdAndId_TouristProvider_Id(touristId, touristProviderId);
    }

    @Override
    public Chat createChat(Long touristId, Long touristProviderId ,Chat chat) {
        validateChat(chat);
        existTouristById(touristId);
        existTouristProviderById(touristProviderId);
        chat.setId(new ChatId());
        chat.getId().setTourist(touristRepository.findTouristById(touristId));
        chat.getId().setTouristProvider(touristProviderRepository.findTouristProviderById(touristProviderId));
        chat.getId().setDateCreated(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    private void validateChat(Chat chat) {
        if (chat.getMessage() == null || chat.getMessage().isEmpty()){
            throw new ValidationException("Message is required");
        }
        if (chat.getMessage().length() > 1000){
            throw new ValidationException("Message is too long");
        }
        if (chat.getSentBy() == null){
            throw new ValidationException("Sent by is required");
        }
    }

    private void existTouristById(Long touristId) {
        if (!touristRepository.existsTouristById(touristId)){
            throw new ResourceNotFoundException("Tourist not found with id: " + touristId);
        }
    }

    private void existTouristProviderById(Long touristProviderId) {
        if (!touristProviderRepository.existsTouristProviderById(touristProviderId)){
            throw new ResourceNotFoundException("Tourist Provider not found with id: " + touristProviderId);
        }
    }
}
