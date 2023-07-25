package com.example.EaseGPT.Controller;

import com.example.EaseGPT.services.impl.GptResponse;
import com.example.EaseGPT.services.impl.ReceivedMessageServiceImpl;
import com.example.EaseGPT.services.impl.SendMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/")
public class RequestHandler {

    @Autowired
    GptResponse gptResponse;

    @Autowired
    ReceivedMessageServiceImpl receivedMessageService;

    @Autowired
    SendMessageServiceImpl sendMessageService;

    String authToken = "hamburger";

    @GetMapping(path = "/")
    public ResponseEntity<Integer> verifyRequest(@RequestParam(value = "hub.mode") String mode,
                                                 @RequestParam(value = "hub.challenge") int challenge,
                                                 @RequestParam(value = "hub.verify_token") String vToken){

        if(vToken.equals(authToken))
            return new ResponseEntity<>(Integer.valueOf(challenge), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> recMessage(@RequestBody String payload) throws Exception{
        String receivedMessage = receivedMessageService.getMessage(payload);

        String generatedMessage = gptResponse.getResponse(receivedMessage + "in one line");

        String recipient = receivedMessageService.getRecipient(payload);

        String sendPayload = "{\n" +
                "  \"messaging_product\": \"whatsapp\",\n" +
                "  \"recipient_type\": \"individual\",\n" +
                "  \"to\": \"" + recipient + "\",\n" +
                "  \"type\": \"text\",\n" +
                "  \"text\": {\n" +
                "    \"preview_url\": true,\n" +
                "    \"body\": \"" + generatedMessage + "\"\n" +
                "  }\n" +
                "}";

        sendMessageService.sendMessage(sendPayload);

        System.out.println("message sent");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}