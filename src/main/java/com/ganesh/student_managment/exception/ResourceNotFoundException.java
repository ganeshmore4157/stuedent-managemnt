package com.ganesh.student_managment.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends Exception {

    private String errorCode;

    private String message;

    public ResourceNotFoundException(String errorCode ,String message){
       this.errorCode=errorCode;
       this.message=message;
    }

    public ResourceNotFoundException(String message) {

    }
}