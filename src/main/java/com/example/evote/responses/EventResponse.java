package com.example.evote.responses;

import com.example.evote.ticket.Ticket_Info;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class EventResponse {
    private int id;
    private String location;

    private String event_info;
    private String price;

    private byte[] eventImage;
    private Date date;
    private int userEnrolled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEvent_info() {
        return event_info;
    }

    public void setEvent_info(String event_info) {
        this.event_info = event_info;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getEventImage() {
        return eventImage;
    }

    public void setEventImage(byte[] eventImage) {
        this.eventImage = eventImage;
    }
    public void setEventImage(String eventImage){
        this.eventImage = Base64.getDecoder().decode(eventImage);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserEnrolled() {
        return userEnrolled;
    }

    public void setUserEnrolled(int userEnrolled) {
        this.userEnrolled = userEnrolled;
    }
}
