package com.example.evote.responses;

import com.example.evote.ticket.EventInfo;
import com.example.evote.ticket.Ticket_Info;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserResponse {
    private String username;
    private byte[] image;
    private List<TicketResponse> ticket_infos = new ArrayList<>();

    private List<EventResponse> allEvent = new ArrayList<>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public void setImage(String image){
        this.image = Base64.getDecoder().decode(image);
    }

    public List<TicketResponse> getTicket_infos() {
        return ticket_infos;
    }

    public void setTicket_infos(List<TicketResponse> ticket_infos) {
        this.ticket_infos = ticket_infos;
    }

    public List<EventResponse> getAllEvent() {
        return allEvent;
    }

    public void setAllEvent(List<EventResponse> allEvent) {
        this.allEvent = allEvent;
    }
}
