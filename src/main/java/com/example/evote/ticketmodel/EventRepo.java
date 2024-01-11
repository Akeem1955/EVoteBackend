package com.example.evote.ticketmodel;

import com.example.evote.ticket.EventInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<EventInfo,Integer> {
}
