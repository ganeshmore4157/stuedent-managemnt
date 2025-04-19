package com.ganesh.student_managment.controller;


import com.ganesh.student_managment.bo.Response;
import com.ganesh.student_managment.constants.ErrorConstants;
import com.ganesh.student_managment.entity.Roles;
import com.ganesh.student_managment.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${url.prefix}/role")
public class RoleController {

    @Autowired
    private RolesService rolesService;


    @PostMapping("/add")
    public ResponseEntity<Response> addRole(@RequestBody Roles roles) throws  Exception{
        rolesService.addrole(roles);
        Response response= new Response();
        response.setMessage("Role added successfully");
        response.setStatus(ErrorConstants.CREATED.toString());
        return ResponseEntity.ok(response);
    }
}
