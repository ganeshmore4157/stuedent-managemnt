package com.ganesh.student_managment.exception.handle;

import com.ganesh.student_managment.bo.Response;
import com.ganesh.student_managment.constants.ErrorConstants;
import com.ganesh.student_managment.exception.EventException;
import com.ganesh.student_managment.exception.ResourceNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EventsExceptionHandler {

    private static final Logger logger= LoggerFactory.getLogger(EventsExceptionHandler.class);

     @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
     logger.error("ResolutionException",ex);
     Response response=new Response();
     response.setStatus(ex.getErrorCode());
     response.setMessage(ex.getMessage());
     return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<Response> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
     logger.error("MethodArgumentNotValidException",ex);
     Map<String,String> map =new HashMap<>();
     ex.getAllErrors().forEach(e ->{
         map.put(((FieldError)e).getField(),((FieldError)e).getDefaultMessage());
     });
     Response response=new Response();
     response.setStatus(ErrorConstants.BAD_REQUEST.toString());
     response.setMessage("Invalid request");
     response.setResult(map);
     return new ResponseEntity<Response>(response,HttpStatus.FORBIDDEN);
     }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> accessDeniedException(AccessDeniedException ex){
         logger.error("AccessDeniedException",ex);
         Response response=new Response();
         response.setStatus(ErrorConstants.FORBIDDEN.toString());
         response.setMessage(ex.getMessage());
         return new ResponseEntity<Response>(response,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EventException.class)
    public ResponseEntity<Response> gosevaExceptionHandler(EventException ex){
         logger.error("EventException",ex);
         Response response=new Response();
         response.setMessage(ex.getMessage());
         response.setStatus(ex.getErrorCode());
         response.setResult(ex.getUniqeNo());
         return new ResponseEntity<Response>(response,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Response> signatureExceptionHandler(SignatureException ex){
         logger.error("SignatureException",ex);
         Response response= new Response();
         response.setStatus(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
         response.setMessage("Token expired or wrong token send");
        return new ResponseEntity<Response>(response,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Response> handleExpiredJwtException(ExpiredJwtException ex){
         logger.error("ExpiredJwtException",ex);
         Response response=new Response();
         response.setStatus(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
         response.setMessage("Token has expired");
         return new ResponseEntity<Response>(response,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Response> handleConstratinViolation(Exception ex, WebRequest request){
        logger.error("TransactionSystemException", ex);
        Throwable cause = ((TransactionSystemException) ex).getRootCause();

        Map<String,String> map = new HashMap<String, String>();
        if(cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintVoilations = ((ConstraintViolationException) cause).getConstraintViolations();
            constraintVoilations.forEach(e->{
                map.put(e.getPropertyPath().toString(), e.getMessageTemplate());
            });
        }
        Response response = new Response();
        response.setStatus(ErrorConstants.BAD_REQUEST.toString());
        response.setMessage("Invalid Request");
        response.setResult(map);
        return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);

    }
}
