package com.example.evote.responses;

import com.example.evote.ticket.EventInfo;
import com.example.evote.ticket.UserTicket;
import jakarta.persistence.*;

import java.util.Base64;
import java.util.Date;


public class TicketResponse {
    private Date date;

    private long ticketId;
    private byte[] image;

    private String username;

    private int  event_info;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEvent_info() {
        return event_info;
    }

    public void setEvent_info(int event_info) {
        this.event_info = event_info;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

}
