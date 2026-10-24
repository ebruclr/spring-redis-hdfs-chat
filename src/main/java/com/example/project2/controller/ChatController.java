package com.example.project2.controller;

import com.example.project2.dto.ChatMessage;
import com.example.project2.dto.ChatResponse;
import com.example.project2.service.OllamaChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final OllamaChatService ollamaChatService;

    @PostMapping
    public ResponseEntity<ChatResponse> processChat(@RequestBody ChatMessage message){
        String response = ollamaChatService.chat(message.getMessage());
        return ResponseEntity.ok(new ChatResponse(response));
    }
}
