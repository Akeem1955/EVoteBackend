package com.example.evote.ticketmodel;

import com.example.evote.ticket.Ticket_Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketReceipt extends JpaRepository<Ticket_Info,Long> {
}
