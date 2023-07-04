package com.example.EaseGPT.Controller;

import com.example.EaseGPT.model.request.ReceivedMessage;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class RequestHandler {
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
        ReceivedMessage message = new Gson().fromJson(payload , ReceivedMessage.class);

        return new ResponseEntity<>(HttpStatus.OK);
        
    }

}





