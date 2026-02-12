package com.load.doldari.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient inputAnalystChatClinet(ChatClient.Builder builder) {
        return builder.defaultSystem("시스템 프롬프트...").build();
    }
}
