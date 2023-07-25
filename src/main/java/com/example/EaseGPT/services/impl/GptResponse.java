package com.example.EaseGPT.services.impl;

import com.example.EaseGPT.services.GptResponseService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;


import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GptResponse implements GptResponseService {

    Dotenv dotenv = Dotenv.load();
    String token = dotenv.get("OPENAI_API_KEY");

    final List<ChatMessage> messages = new ArrayList<>();
    final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(),
            "You are a whatsapp bot named EaseGPT and help people with their day to day questions");

    OpenAiService service = new OpenAiService(token);

    @Override
    public String getResponse(String receivedMessage) {

        messages.add(new ChatMessage(ChatMessageRole.USER.value(), receivedMessage));
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(50)
                .build();

        ChatMessage responseMessage = service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();

        return responseMessage.getContent();
    }
}
