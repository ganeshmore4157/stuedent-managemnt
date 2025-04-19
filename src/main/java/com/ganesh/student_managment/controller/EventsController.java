package com.ganesh.student_managment.controller;

import com.ganesh.student_managment.bo.Response;
import com.ganesh.student_managment.constants.ErrorConstants;
import com.ganesh.student_managment.entity.Events;
import com.ganesh.student_managment.service.EventsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${url.prefix}/events")
public class EventsController {

    @Autowired
    EventsService eventsService;

    @PostMapping
    public ResponseEntity<Response> addEvents(@RequestBody Events events) throws Exception{
        eventsService.addEvents(events);
        Response response=new Response();
        response.setMessage("Events added sucessfully");
        response.setStatus(ErrorConstants.SUCCESS.toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public  ResponseEntity<Response> getallEvents() throws Exception{
        Response response=new Response();
        response.setStatus(ErrorConstants.SUCCESS.toString());
        response.setMessage("Events List");
        response.setResult(eventsService.allEventsList());
        response.setListCount(eventsService.allEventsList().stream().count());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Response> updateEvents(@PathVariable UUID eventId,
                                                 @Valid @RequestBody Events events) throws Exception{
        eventsService.updateEvents(events,eventId);
        Response response=new Response();
        response.setMessage("Events update successfully");
        response.setStatus(ErrorConstants.SUCCESS.toString());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Response> deleteEventById(@PathVariable UUID eventId) throws Exception{
        eventsService.deleteEventById(eventId);
        Response response=new Response();
        response.setStatus(ErrorConstants.SUCCESS.toString());
        response.setMessage("Event delete successfully");
        return ResponseEntity.ok(response);
    }

}
