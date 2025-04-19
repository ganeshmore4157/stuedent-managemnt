package com.ganesh.student_managment.controller;

import com.ganesh.student_managment.bo.Response;
import com.ganesh.student_managment.constants.ErrorConstants;
import com.ganesh.student_managment.entity.Payment;
import com.ganesh.student_managment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("${url.prefix}/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Response> payment(@Valid @RequestBody Payment payment) throws Exception{
        paymentService.processPayment(payment);
        Response response=new Response();
        response.setStatus(ErrorConstants.SUCCESS.toString());
        response.setMessage("Payment successfully");
        return ResponseEntity.ok(response);
    }
}
