package com.ganesh.student_managment.repository;

import com.ganesh.student_managment.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket,UUID> {

}
