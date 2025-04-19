package com.ganesh.student_managment.repository;


import com.ganesh.student_managment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {

    Users findByEmail(String email) throws UsernameNotFoundException;

    Users findByMobile(String mobile) throws UsernameNotFoundException;
    Users findByEmailOrMobile(String email, String mobile) throws UsernameNotFoundException;
}
