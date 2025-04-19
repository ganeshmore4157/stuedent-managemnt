package com.ganesh.student_managment.service;

import com.ganesh.student_managment.entity.Payment;

import java.util.UUID;

public interface PaymentService {

    void processPayment(Payment payment) throws Exception;
}
