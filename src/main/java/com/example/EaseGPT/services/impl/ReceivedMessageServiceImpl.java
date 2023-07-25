package com.example.EaseGPT.services.impl;


import com.example.EaseGPT.model.request.ReceivedMessage;
import com.example.EaseGPT.services.ReceiveMessageService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class ReceivedMessageServiceImpl implements ReceiveMessageService {
    @Override
    public String getMessage(String payload){
        ReceivedMessage message = new Gson().fromJson(payload , ReceivedMessage.class);

        return message.getBody();
    }

    public String getRecipient(String payload){
        ReceivedMessage message = new Gson().fromJson(payload , ReceivedMessage.class);

        return message.getrecepeient();
    }
}
