package com.ganesh.student_managment.repository;

import com.ganesh.student_managment.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface EventsRepository extends JpaRepository<Events, UUID> {

    Optional<Events> findByStartDate(Date startDate) throws Exception;
}
