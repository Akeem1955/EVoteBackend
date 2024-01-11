package com.example.evote.ticket;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class EventInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String location;
    @Column(name = "event_info", length = 100000)
    private String event_info;
    private String price;
    @Lob
    @Column(name = "eventImage", columnDefinition = "LONGBLOB")
    private byte[] eventImage;
    private Date date;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Ticket_Info> ticket_info = new ArrayList<>();

    public List<Ticket_Info> getTicket_infos() {
        return ticket_info;
    }

    public void setTicket_infos(List<Ticket_Info> ticket_infos) {
        this.ticket_info= ticket_infos;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent_info() {
        return event_info;
    }

    public void setEvent_info(String event_info) {
        this.event_info = event_info;
    }

}
