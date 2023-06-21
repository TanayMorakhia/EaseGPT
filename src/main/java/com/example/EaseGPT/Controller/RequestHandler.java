package com.example.EaseGPT.Controller;

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
    public ResponseEntity<HttpStatus> recMessage(@RequestBody Map<String, Object> payload) throws Exception{
        System.out.println(payload);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}





