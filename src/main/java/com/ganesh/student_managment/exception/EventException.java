package com.ganesh.student_managment.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventException extends Exception {

   private String errorCode;

   private String message;

   private String uniqeNo;

public EventException(String errorCode, String message, String uniqeNo){
    super();
    this.errorCode=errorCode;
    this.message=message;
    this.uniqeNo=uniqeNo;
}

public EventException(String errorCode, String message){
     super();
     this.errorCode=errorCode;
     this.message=message;
}
}
