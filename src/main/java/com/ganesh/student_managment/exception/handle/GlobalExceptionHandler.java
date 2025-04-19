package com.ganesh.student_managment.exception.handle;

import com.ganesh.student_managment.bo.Response;
import com.ganesh.student_managment.constants.ErrorConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

     private static final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

     @ExceptionHandler
     public ResponseEntity<Response> globalExceptionHandler(Exception ex){
        logger.error("Global exception handler",ex);
        Response response=new Response();
        response.setStatus(ErrorConstants.INVALID.toString());
        response.setMessage("Internal Server Error");
        return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
     }

}
