package com.example.project2.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OllamaChatService {
    private final ChatModel chatModel;

     public String chat(String message){
         return chatModel.call(message);
     }
}
