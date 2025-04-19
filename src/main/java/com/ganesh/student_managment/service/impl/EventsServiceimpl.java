package com.ganesh.student_managment.service.impl;

import com.ganesh.student_managment.constants.ErrorConstants;
import com.ganesh.student_managment.constants.Role;
import com.ganesh.student_managment.entity.Events;
import com.ganesh.student_managment.entity.Roles;
import com.ganesh.student_managment.exception.EventException;
import com.ganesh.student_managment.exception.ResourceNotFoundException;
import com.ganesh.student_managment.repository.EventsRepository;
import com.ganesh.student_managment.repository.RoleRepository;
import com.ganesh.student_managment.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class EventsServiceimpl implements EventsService {

    @Autowired
    EventsRepository eventsRepo;

    @Autowired
    RoleRepository roleRepo;

    @Override
    public void addEvents(Events events) throws Exception {
       Optional <Events> checkEvents =eventsRepo.findByStartDate(events.getStartDate());
        if (checkEvents.isPresent()){
            throw new EventException(ErrorConstants.UNIQUE_KEY_EXISTS.toString(), "Start date all ready book");
        }
        Optional<Roles> role = roleRepo.findByName(Role.ORGANIZER.toString());
        if (role.isPresent()) {
            events.setRoles(role.get());
        } else {
            throw new EventException(ErrorConstants.NOT_FOUND.toString(), "Organizer role not found");
        }
        eventsRepo.save(events);
    }

    @Override
    public List<Events> allEventsList() throws Exception {
           return eventsRepo.findAll();
    }

    @Override
    public Events getEventsById(UUID eventsId) throws Exception {
     return eventsRepo.findById(eventsId).orElseThrow(
             () -> new ResourceNotFoundException(ErrorConstants.NOT_FOUND.toString(),"Events not found"));
    }

    @Override
    public void updateEvents(Events events, UUID eventsId) throws Exception {
       Events existEvents=getEventsById(eventsId);
        if (!events.getStartDate().equals(existEvents.getStartDate())){
           Optional <Events> checkDate=eventsRepo.findByStartDate(events.getStartDate());
          if (checkDate.isPresent()){
             throw new EventException(ErrorConstants.UNIQUE_KEY_EXISTS.toString(), "Start date exists");
          }
          existEvents.setDescription(events.getDescription());
          existEvents.setPrice(events.getPrice());
          existEvents.setEndDate(events.getEndDate());
          existEvents.setStartDate(events.getStartDate());
          existEvents.setTitle(events.getTitle());
          existEvents.setEventCategory(events.getEventCategory());
          existEvents.setPrice(events.getPrice());
          existEvents.setOrganizer(events.getOrganizer());
        }
        eventsRepo.save(existEvents);
    }

    @Override
    public void deleteEventById(UUID eventId) throws Exception {
        // Check if the event exists
        if (!eventsRepo.existsById(eventId)) {
            throw new EventException(ErrorConstants.NOT_FOUND.toString(), "Events not found");
        }

        // Delete the event
        eventsRepo.deleteById(eventId);
    }



}
