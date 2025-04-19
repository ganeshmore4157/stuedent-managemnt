package com.ganesh.student_managment.service;

import com.ganesh.student_managment.entity.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    void addTicket(Ticket ticket, UUID eventId) throws Exception;

    List<Ticket> getTickt() throws Exception;
}
