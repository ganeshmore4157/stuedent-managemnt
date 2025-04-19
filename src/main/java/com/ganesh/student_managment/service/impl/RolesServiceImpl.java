package com.ganesh.student_managment.service.impl;

import com.ganesh.student_managment.constants.ErrorConstants;
import com.ganesh.student_managment.entity.Roles;
import com.ganesh.student_managment.exception.EventException;
import com.ganesh.student_managment.repository.RoleActionRepository;
import com.ganesh.student_managment.repository.RoleRepository;
import com.ganesh.student_managment.repository.UsersRepository;
import com.ganesh.student_managment.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleActionRepository roleActionRepository;

    @Override
    public void addrole(Roles roles) throws Exception {
        Optional<Roles> existRole=roleRepository.findByName(roles.getName());
        if (existRole.isPresent()){
            throw new EventException(ErrorConstants.INVALID.toString(), "Role name already exists");
        }
        roleRepository.save(roles);
        roles.getActionList().forEach(action->{
        action.setRoles(roles);
        roleActionRepository.save(action);
        });

    }
}
