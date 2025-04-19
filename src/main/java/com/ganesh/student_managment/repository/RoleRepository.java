package com.ganesh.student_managment.repository;

import com.ganesh.student_managment.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Roles, UUID> {

  Optional<Roles>findByName(String string) throws Exception;

}
