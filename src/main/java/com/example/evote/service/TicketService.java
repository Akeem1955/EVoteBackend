package com.example.evote.service;

import com.example.evote.responses.EventResponse;
import com.example.evote.responses.TicketResponse;
import com.example.evote.responses.UserResponse;
import com.example.evote.ticket.EventInfo;
import com.example.evote.ticket.Ticket_Info;
import com.example.evote.ticket.UserTicket;
import com.example.evote.ticketmodel.EventRepo;
import com.example.evote.ticketmodel.TicketReceipt;
import com.example.evote.ticketmodel.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

@Service
public class TicketService {
    @Autowired
    UserRepo user;
    @Autowired
    EventRepo event;
    @Autowired
    TicketReceipt ticket_repo;
    public UserResponse processReciept(String username,int id) throws IOException {
        UserTicket userTicket = user.findById(username).get();
        EventInfo info = event.findById(id).get();
        Ticket_Info ticket_info = new Ticket_Info();
        ticket_info.setEvent_id(id);


        ticket_info.setDate(Calendar.getInstance().getTime());


        String seat_no = String.valueOf(info.getTicket_infos().size() + 1);
        String event_date = info.getDate().toString();
        String event_location = info.getLocation();


        ticket_info.setImage(generateImageReceipt(username,seat_no,event_date,event_location));
        userTicket.getTicket_infos().add(ticket_info);
        info.getTicket_infos().add(ticket_info);
        ticket_repo.saveAndFlush(ticket_info);
        System.out.println("Yey Bug Found line 50");
        user.saveAndFlush(userTicket);
        System.out.println("Yey Bug Found line 52");
        event.saveAndFlush(info);
        System.out.println("Yey Bug Found line 54");
        System.out.println("Yey Bug Found line 56");


        UserResponse response = new UserResponse();
        //EventResponse e_response=new EventResponse();
        TicketResponse responses = new TicketResponse();


       //event
//        e_response.setId(info.getId());
//        e_response.setUserEnrolled(Integer.parseInt(seat_no));
//        e_response.setEventImage(info.getEventImage());
//        e_response.setLocation(info.getLocation());
//        e_response.setPrice(info.getPrice());
//        e_response.setDate(info.getDate());
//        e_response.setEvent_info(info.getEvent_info());

        //e_ticket
        response.setImage(userTicket.getImage());
        responses.setTicketId(ticket_info.getTicketId());
        responses.setDate(ticket_info.getDate());
        responses.setImage(ticket_info.getImage());
        responses.setUsername(userTicket.getUsername());
        responses.setEvent_info(info.getId());
        //UserResponse
        response.setUsername(username);
        response.getTicket_infos().add(responses);


        System.out.println("User response last phase");
        return response;
    }
    private byte[] generateImageReceipt(String username,String seat_no,String date,String location) throws IOException {

        int width = 500;
        int height = 300;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage gdscIcon = ImageIO.read(new File("dev.png"));
        System.out.println("Image Processing problem finding...");


        Graphics2D graphics = image.createGraphics();
        int x = image.getWidth() / 2 - 50;
        int y = 20;

        graphics.setColor(new Color(5, 35, 58));
        graphics.fillRect(0, 0, width, height);
        //Admit one
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(Font.SERIF,Font.BOLD,20));
        //graphics.rotate(-Math.PI / 2, 430,200);
        graphics.drawString("ADMIT ONE" , 430,200);
        //graphics.rotate(Math.PI / 2, 430,200);

        //Gdsc Icon
        graphics.drawImage(gdscIcon,10,10,null);
        graphics.setColor(new Color(0xFFFFFF));
        graphics.setFont(new Font("Ariel",Font.PLAIN,16));
        graphics.drawString("Google Developer Student Clubs",80,20);
        graphics.setFont(new Font("Ariel",Font.PLAIN,14));
        graphics.drawString("University Of Abuja", 140,50);

        //container
        graphics.draw3DRect(10,100,300,150,true);

        //Information
        graphics.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
        graphics.drawString("Date: " + date, 15,120);
        graphics.setFont(new Font(Font.MONOSPACED,Font.PLAIN,15));
        graphics.drawString("Location: " + location, 15,160);
        graphics.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));
        graphics.drawString("Seat No: "+seat_no , 15,200);
        graphics.drawString("Username: "+username , 15,240);
        //barcodde
        graphics.fillRect(400, 0, 100, height);
        BufferedImage barcode = new BarCode().barcode(username+seat_no+date+location,100,100);
        graphics.drawImage(barcode,400,10,null);
        graphics.setColor(new Color(0x023B5E));
        graphics.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
        graphics.rotate(-Math.PI / 2, 430,250);
        graphics.drawString("ADMIT ONE" , 430,250);
        graphics.rotate(Math.PI / 2, 430,250);

        graphics.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        System.out.println("Image Processsing Finished");
        return baos.toByteArray();
    }

}
