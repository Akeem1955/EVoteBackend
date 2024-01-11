package com.example.evote.controller;

import com.example.evote.responses.UserResponse;
import com.example.evote.service.TicketService;
import com.example.evote.ticketmodel.EventRepo;
import com.example.evote.ticketmodel.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateTicket {
    @Autowired
    UserRepo repo;
    @Autowired
    EventRepo e;
    @Autowired
    TicketService service;
    @PostMapping("/generate_ticket")
    ResponseEntity<UserResponse> generateTicket(@RequestPart("username")String username, @RequestPart("event_id") int id){
        username = username.substring(1,username.length()-1);
        System.out.println(id + " ---------->eventId");
        if(!repo.existsById(username)){
            return ResponseEntity.status(404).body(null);
        }
        if(!e.existsById(id)){
            return ResponseEntity.status(404).body(null);
        }
       try {
           UserResponse response = service.processReciept(username,id);
           return ResponseEntity.ok().body(response);
       }catch (Exception e){

           System.out.println(e.getLocalizedMessage());
           return ResponseEntity.status(500).body(null);
       }
    }
}
