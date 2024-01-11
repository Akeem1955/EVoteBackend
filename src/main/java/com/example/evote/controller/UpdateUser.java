package com.example.evote.controller;

import com.example.evote.responses.UserResponse;
import com.example.evote.ticket.UserTicket;
import com.example.evote.ticketmodel.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
public class UpdateUser { 
    @Autowired
    UserRepo repo;
    @PostMapping("/update_profile")
    ResponseEntity<Messages> update(@RequestPart("username")String username, MultipartFile image) {
        username = username.substring(1,username.length()-1);
        System.out.println(username + "Helooo BUG HEre.....");
        image.
        Optional<UserTicket> d = repo.findById(username);
        if(d.isEmpty()){
            System.out.println(username + "Helooo BUG HEre.....");
            return ResponseEntity.status(401).body(null);
        }
        UserTicket user = d.get();
        try {
            System.out.println("Update User lets Begin line 30");
            user.setImage(image.getBytes());
            System.out.println("Update User lets Begin line 32");
            repo.saveAndFlush(user);
            Messages m = new Messages();

            m.setMessage("Sucess");
            System.out.println("weeeeeee Sucess");
            return ResponseEntity.ok().body(m);
        }catch (Exception e){
            System.out.println(image == null);
            System.out.println(e.getLocalizedMessage() + " UpdateUser line 30");
            return ResponseEntity.badRequest().body(null);
        }
    }
}
