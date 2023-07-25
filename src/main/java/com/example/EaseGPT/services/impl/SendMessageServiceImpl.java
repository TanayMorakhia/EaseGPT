package com.example.EaseGPT.services.impl;

import com.example.EaseGPT.services.SendMessageService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SendMessageServiceImpl implements SendMessageService {
    WebClient.Builder webBuilder = WebClient.builder();
    Dotenv dotenv = Dotenv.load();

    public void sendMessage(String payload){
//         webBuilder.build()
//                .post()
//                .uri("https://graph.facebook.com/v17.0/FROM_PHONE_NUMBER_ID/messages")
//                .header("Authorization", "Bearer " + dotenv.get("ACCESS_TOKEN"))
//                .header("Content-Type","application/json")
//                .body(Mono.just(payload), String.class)
//                .retrieve()
//                .bodyToMono(String.class);
        String jsonResponse = webBuilder.build()
                .post()
                .uri("https://graph.facebook.com/v17.0/108917878916426/messages") // Replace with your API endpoint
                .header("Authorization", "Bearer " + dotenv.get("ACCESS_TOKEN"))
                .header("Content-Type","application/json")
                .bodyValue(payload) // Set the request body (if required)
                .retrieve()
                .bodyToMono(String.class) // Set the response type to String
                .block();

        System.out.println(jsonResponse);
    }
}
