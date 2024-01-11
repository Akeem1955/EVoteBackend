package com.example.evote.ticket;

import jakarta.persistence.*;

import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserTicket {
    @Id
    private String username;
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;
    private String password;
    private String phone;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Ticket_Info> ticket_infos = new ArrayList<>();

    public List<Ticket_Info> getTicket_infos() {
        return ticket_infos;
    }

    public void setTicket_infos(List<Ticket_Info> ticket_infos) {
        this.ticket_infos = ticket_infos;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
