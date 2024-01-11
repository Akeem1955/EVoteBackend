package com.example.evote.controller;

import com.example.evote.responses.EventResponse;
import com.example.evote.responses.TicketResponse;
import com.example.evote.responses.UserResponse;
import com.example.evote.ticket.EventInfo;
import com.example.evote.ticket.UserTicket;
import com.example.evote.ticketmodel.EventRepo;
import com.example.evote.ticketmodel.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
public class Ticket {
    @Autowired
    EventRepo repo;
    @Autowired
    UserRepo userRepo;
    @PostMapping("/addEvent")
    String addEvent(
            @RequestPart("location") String location,
            @RequestPart("event_info") String event_info,
            @RequestPart("date") String date,
            @RequestPart("event_image")MultipartFile image
            ){
        EventInfo info = new EventInfo();
        info.setPrice("Free");
        info.setEvent_info(event_info);
        info.setLocation(location);
        try {
            Calendar calendar = new GregorianCalendar();
            String d[] =date.split("-");
            calendar.set(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]),Integer.parseInt(d[3]),Integer.parseInt(d[4]),0);
            info.setEventImage(image.getBytes());
            info.setDate(calendar.getTime());
            repo.saveAndFlush(info);

        }catch (Exception e){
            return "Error ocuured When Adding Event Check the parameter " + e.getLocalizedMessage();
        }
        return "Sucess";
    }

    @PostMapping("/userResponse")
    UserResponse userResponse(@RequestPart("username") String username){
        username = username.substring(1,username.length()-1);
        System.out.println("User Response Begining Phase");
        if(userRepo.existsById(username) != true){
            return null;
        }
        UserResponse response = new UserResponse();
        try {
            System.out.println("Bug Killer Looking for bugs...   3");
            UserTicket ticket = userRepo.findById(username).get();
            System.out.println("Bug Killer Looking for bugs...");
            List<EventInfo> info = repo.findAll();

            response.setUsername(username);
            response.setImage(ticket.getImage());
            System.out.println("Bug Killer Looking for bugs...   1");
            for (int i = 0; i < ticket.getTicket_infos().size(); i++) {
                System.out.println("User response second phase");
                TicketResponse responses = new TicketResponse();
                System.out.println("Bug Killer Looking for bugs...  2");
                responses.setTicketId(ticket.getTicket_infos().get(i).getTicketId());
                responses.setDate(ticket.getTicket_infos().get(i).getDate());

                responses.setImage(ticket.getTicket_infos().get(i).getImage());
                responses.setUsername(ticket.getUsername());
                responses.setEvent_info(ticket.getTicket_infos().get(i).getEvent_id());
                response.getTicket_infos().add(responses);
            }
            System.out.println("Bug Killer Looking for bugs...  2");
            for (int i = 0; i < info.size(); i++) {
                System.out.println("User response third phase");

                EventResponse eves = new EventResponse();
                eves.setDate(info.get(i).getDate());
                eves.setId(info.get(i).getId());
                eves.setUserEnrolled(info.get(i).getTicket_infos().size());
                eves.setLocation(info.get(i).getLocation());
                eves.setEventImage(info.get(i).getEventImage());
                eves.setEvent_info(info.get(i).getEvent_info());
                eves.setPrice(info.get(i).getPrice());
                response.getAllEvent().add(eves);
            }
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        System.out.println("last phase,l0o");
        return  response;
    }
}
