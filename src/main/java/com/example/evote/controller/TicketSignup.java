package com.example.evote.controller;

import com.example.evote.service.SmsService;
import com.example.evote.ticket.UserTicket;
import com.example.evote.ticketmodel.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketSignup {
    @Autowired
    UserRepo repo;
    @Autowired
    SmsService sms;

    @PostMapping("/verify_otp")
    ResponseEntity<Messages> verifyOtp(@RequestPart("otp") String otp, @RequestPart("username") String username){
        Messages messages = new Messages();
        username = username.substring(1,username.length()-1);
        otp = otp.substring(1,otp.length()-1);
        System.out.println("In the verify phase begin");
        if(!repo.existsById(username)){
            System.out.println("First Check!");
            messages.setMessage("User Does Not Exist");
            return ResponseEntity.status(401).body(messages);
        }
        UserTicket ticket = repo.findById(username).get();
        System.out.println(otp);
        if(!sms.verifySms(otp,ticket.getPhone())){
            System.out.println(otp);
            System.out.println("Second Phase");
            System.out.println(otp);
            messages.setMessage("Authethication Failed");
            messages.setOtpMsg("Invalid or Expired!");
            return ResponseEntity.status(401).body(messages);
        }
        System.out.println("Last Phase" +
                "");

        messages.setMessage("Sucess");
        messages.setOtpMsg("Valid");
        return ResponseEntity.ok().body(messages);
    }

    @PostMapping("/new_user_ticket")
    ResponseEntity<Messages> signup(
            @RequestPart("username") String username,
            @RequestPart("password") String password,
            @RequestPart("phone") String phone
    ){
        username = username.substring(1,username.length()-1);
        password = password.substring(1,password.length()-1);
        phone = phone.substring(1,phone.length()-1);
        Messages messages = new Messages();
        if(repo.existsById(username)   || repo.existsByPhone(phone)){
            messages.setMessage("Username or Phone Already Exist");
            return ResponseEntity.status(409).body(messages);
        }

        UserTicket ticket = new UserTicket();
        try {
            ticket.setPassword(password);
            ticket.setPhone(phone);
            ticket.setUsername(username);
            sms.sendSms(ticket.getPhone());
            repo.saveAndFlush(ticket);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            messages.setMessage("Server Error :(");
            return  ResponseEntity.internalServerError().body(messages);

        }
        messages.setMessage("Success");
        return ResponseEntity.ok(messages);
    }
}
