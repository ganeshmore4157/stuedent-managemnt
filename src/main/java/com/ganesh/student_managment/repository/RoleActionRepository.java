package com.ganesh.student_managment.repository;

import com.ganesh.student_managment.entity.RoleAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleActionRepository extends JpaRepository<RoleAction, UUID> {
}
