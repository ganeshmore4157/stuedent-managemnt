package com.ganesh.student_managment.controller;

import com.ganesh.student_managment.bo.Response;
import com.ganesh.student_managment.bo.UserBO;
import com.ganesh.student_managment.constants.ErrorConstants;
import com.ganesh.student_managment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${url.prefix}/user")
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
   public ResponseEntity<Response> userLogin(@RequestBody UserBO userBO) throws Exception{
    Response response=new Response();
    response.setStatus(ErrorConstants.SUCCESS.toString());
    response.setMessage("Logged successfully");
    response.setResult(userService.userLogin(userBO));
    return ResponseEntity.ok(response);
   }

   @PostMapping("/add")
   public ResponseEntity<Response> adduser(@RequestBody UserBO userBO) throws Exception{
        userService.registerUser(userBO);
        Response response =new Response();
        response.setMessage("User added successfully");
        response.setStatus(ErrorConstants.CREATED.toString());
//        response.setResult(userService.registerUser(userBO));
        return ResponseEntity.ok(response);
    }
}
