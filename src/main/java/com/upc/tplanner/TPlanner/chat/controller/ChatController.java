package com.upc.tplanner.TPlanner.chat.controller;

import com.upc.tplanner.TPlanner.chat.dto.ChatRequest;
import com.upc.tplanner.TPlanner.chat.dto.ChatResponse;
import com.upc.tplanner.TPlanner.chat.dto.mapper.ChatMapper;
import com.upc.tplanner.TPlanner.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    ChatService chatService;

    @Transactional(readOnly = true)
    @GetMapping(value = "/chats")
    public ResponseEntity<List<ChatResponse>> getAllChats() {
        var chats = chatService.getAllChats();
        return new ResponseEntity<List<ChatResponse>>(ChatMapper.INSTANCE.chatsToChatsResponse(chats), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/TouristId/{touristId}/TouristProviderId/{touristProviderId}/chats")
    public ResponseEntity<ChatResponse> createChat(@PathVariable(value = "touristId") Long touristId,
                                                   @PathVariable(value = "touristProviderId") Long touristProviderId,
                                                   @RequestBody ChatRequest chatRequest) {
        var chat = ChatMapper.INSTANCE.chatRequestToChat(chatRequest);
        var chatCreated = chatService.createChat(touristId, touristProviderId, chat);
        return new ResponseEntity<ChatResponse>(ChatMapper.INSTANCE.chatToChatResponse(chatCreated), HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/chats/filterByTouristIdAndTouristProviderId")
    public ResponseEntity<List<ChatResponse>> getChatsByTouristIdAndTouristProviderId(@RequestParam(value = "touristId") Long touristId,
                                                                                      @RequestParam(value = "touristProviderId") Long touristProviderId) {
        var chats = chatService.getChatsByTouristIdAndTouristProviderId(touristId, touristProviderId);
        return new ResponseEntity<List<ChatResponse>>(ChatMapper.INSTANCE.chatsToChatsResponse(chats), HttpStatus.OK);
    }

}
