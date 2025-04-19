package com.ganesh.student_managment.bo;

import com.ganesh.student_managment.constants.Gender;
import com.ganesh.student_managment.entity.Roles;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class UserBO  {


    private UUID userId;

    private String fullName;

    private String email;

    private String mobile;

    private String photo;

    private String password;

    private String newPassword;

    private String otp;

    private Gender gender;

    private Date birthDate;

    private String organizationName;

    private String employeeId;

    private boolean status;

    private String website;

    private String deviceId;

    private String deviceInfo;

    private String sessionToken;

    private String encodedPhoto;

    private Roles role;

    private String registrationNumber;

}
