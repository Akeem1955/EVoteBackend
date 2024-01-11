package com.example.evote.controller;

import com.example.evote.service.SmsService;
import com.example.evote.ticket.UserTicket;
import com.example.evote.ticketmodel.UserRepo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketSignin {
    @Autowired
    UserRepo repo;
    @Autowired
    SmsService sms;


    @PostMapping("/verify_user")
    ResponseEntity<Messages> verifyUser(
            @RequestBody JsonNode jsonNode
    ){
        Messages messages = new Messages();
        String username = jsonNode.get("username").asText();
        String password = jsonNode.get("password").asText();
        System.out.println(username + " <---> " + password);

        if(!repo.existsById(username)){
            messages.setMessage("Username Or Password Incorrect!");
            return ResponseEntity.status(401).body(messages);
        }
        UserTicket ticket =repo.findById(username).get();
        if(!ticket.getPassword().equals(password)){
            messages.setMessage("Username Or Password Incorrect!");
            return ResponseEntity.status(401).body(messages);
        }
        sms.sendSms(ticket.getPhone());
        messages.setMessage("Sucess");
        return ResponseEntity.ok().body(messages);
    }

    @PostMapping("/" +
            "")
    ResponseEntity<Messages> resendOtp(@RequestBody JsonNode jsonNode){
        Messages messages = new Messages();
        String username = jsonNode.get("username").asText();

        if(repo.existsById(username) != true){
            messages.setMessage("Username Or Password Incorrect!");
            return ResponseEntity.status(401).body(messages);
        }
        UserTicket ticket =repo.findById(username).get();
        sms.sendSms(ticket.getPhone());
        messages.setMessage("Sucess");
        messages.setOtpMsg("Sent");
        return ResponseEntity.ok().body(messages);
    }
}
