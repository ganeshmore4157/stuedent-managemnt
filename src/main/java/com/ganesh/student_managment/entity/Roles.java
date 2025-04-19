package com.ganesh.student_managment.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Roles  {


    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name="status",columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean status;

   @OneToMany(mappedBy = "roles")
    List<RoleAction> actionList= new ArrayList<>();

}
