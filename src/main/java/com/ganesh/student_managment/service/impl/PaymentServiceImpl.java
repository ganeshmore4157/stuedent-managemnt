package com.ganesh.student_managment.service.impl;

import com.ganesh.student_managment.constants.ErrorConstants;
import com.ganesh.student_managment.entity.Payment;
import com.ganesh.student_managment.entity.Ticket;
import com.ganesh.student_managment.entity.Users;
import com.ganesh.student_managment.exception.ResourceNotFoundException;
import com.ganesh.student_managment.repository.PaymentRepository;
import com.ganesh.student_managment.repository.TicketRepository;
import com.ganesh.student_managment.service.PaymentService;
import com.ganesh.student_managment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepo;

    @Autowired
    UserService userService;

    @Autowired
    TicketRepository ticketRepo;

    @Override
    public void processPayment(Payment payment) throws Exception {
        Users existuser=userService.getLoggedUser();
        Ticket ticket=ticketRepo.findById(payment.getTicket().getTickedId()).orElseThrow(
                () ->  new ResourceNotFoundException(ErrorConstants.NOT_FOUND.toString(),"Ticket Not found"));
        payment.setTicket(ticket);
        payment.setUsers(existuser);
        paymentRepo.save(payment);
    }
}
