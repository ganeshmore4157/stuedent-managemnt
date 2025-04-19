package com.ganesh.student_managment.service;

import com.ganesh.student_managment.bo.UserBO;
import com.ganesh.student_managment.entity.Users;

public interface UserService {
    Users userLogin(UserBO userBO) throws Exception;

    void registerUser(UserBO userBO) throws Exception;

    Users getLoggedUser() throws Exception;
}
