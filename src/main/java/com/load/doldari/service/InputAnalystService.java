package com.load.doldari.service;

import com.load.doldari.dto.InputAnalysisResult;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class InputAnalystService {
    private final ChatClient chatClient;

    public InputAnalystService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public InputAnalysisResult analyze(String userInput) {
        return chatClient.prompt()
                .user(userInput)
                .call()
                .entity(InputAnalysisResult.class);
    }
}
