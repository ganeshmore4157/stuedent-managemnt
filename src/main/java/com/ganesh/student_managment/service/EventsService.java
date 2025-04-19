package com.ganesh.student_managment.service;

import com.ganesh.student_managment.entity.Events;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface EventsService {

    void addEvents(Events events) throws Exception;

    List<Events> allEventsList() throws Exception;

    Events getEventsById(UUID eventsId) throws Exception;

    void updateEvents(Events events,UUID eventsId) throws Exception;

    void deleteEventById(UUID eventId) throws Exception;
}
