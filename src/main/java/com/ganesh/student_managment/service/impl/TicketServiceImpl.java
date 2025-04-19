package com.ganesh.student_managment.service.impl;

import com.ganesh.student_managment.constants.ErrorConstants;
import com.ganesh.student_managment.entity.Events;
import com.ganesh.student_managment.entity.Ticket;
import com.ganesh.student_managment.exception.ResourceNotFoundException;
import com.ganesh.student_managment.repository.EventsRepository;
import com.ganesh.student_managment.repository.TicketRepository;
import com.ganesh.student_managment.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventException;

import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepo;

    @Autowired
    EventsRepository eventsRepository;

    @Override
    public void addTicket(Ticket ticket, UUID eventId) throws Exception {
        Events events=eventsRepository.findById(eventId).orElseThrow(
                ()-> new ResourceNotFoundException(ErrorConstants.NOT_FOUND.toString(),"Event not found"));
        if (!events.isAvailable()) {
            throw new Exception("Event is not available for booking");
        }
        ticket.setEvent(events);
        ticketRepo.save(ticket);
    }

    @Override
    public List<Ticket> getTickt() throws Exception {
        return null;
    }
}
