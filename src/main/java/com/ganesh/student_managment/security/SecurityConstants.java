package com.ganesh.student_managment.security;

public class SecurityConstants {

    public static final String SECRET = "jwtTokenKey";

    public static final String[] GET_ALLOWED_URL = {"/v1/file/**", "/v1/content/latest/**", "/v1/taluka/active/**",
            "/v1/sanstha/get/**" };

    public static final String[] ALLOWED_URL = {"/v1/user/login","/v1/user/add" ,"/v1/user/applicant/login","/v1/events"
    };

}
