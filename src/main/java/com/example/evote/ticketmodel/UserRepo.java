package com.example.evote.ticketmodel;

import com.example.evote.ticket.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepo extends JpaRepository<UserTicket,String> {
    boolean existsByPhone(String phone);
}
