package com.ganesh.student_managment.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

    private String status;
    private String message;
    private Object result;
    private Long listCount;
    private String appVersion;
    private String buildVersion;

    public Response(String message,String status){
       this.status=status;
       this.message=message;
    }
    public Response(){

    }
}
