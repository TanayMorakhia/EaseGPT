package com.example.EaseGPT.model.request;

import java.util.List;

public class SendMessage {
    final private String messagingProduct = "whatsapp";
    final private String recipientType = "individual";
    private String to;
    final private String type = "text";
    private List<Text> text;

    public class Text{
        final private String preview_url = "true";
        private String body;

        public void setBody(String body) {
            this.body = body;
        }
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setTextMessage(String message){
        this.text.get(0).setBody(message);
    }

}

