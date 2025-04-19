package com.ganesh.student_managment.controller;


import com.ganesh.student_managment.bo.Response;
import com.ganesh.student_managment.constants.ErrorConstants;
import com.ganesh.student_managment.entity.Ticket;
import com.ganesh.student_managment.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${url.prefix}/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/{eventId}")
    public ResponseEntity<Response> addTicket(@RequestBody Ticket ticket,@PathVariable UUID eventId) throws Exception{
        ticketService.addTicket(ticket,eventId);
        Response response=new Response();
        response.setStatus(ErrorConstants.SUCCESS.toString());
        response.setMessage("Ticked book successfully");
        return ResponseEntity.ok(response);
    }
}
