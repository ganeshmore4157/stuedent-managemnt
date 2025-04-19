package com.ganesh.student_managment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ganesh.student_managment.constants.Modules;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode()
@Table(name = "role_action", indexes = {
        @Index(name = "created_by", columnList = "created_by"),
        @Index(name = "last_modified_by", columnList = "last_modified_by")})
public class RoleAction extends Auditable<UUID> {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name="role_action_id")
    private UUID roleActionId;

    @Enumerated(EnumType.STRING)
    @Column(name="module",length = 50,nullable = false)
    private Modules modules;

    @Column(name="add_action",columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean addAction;

    @Column(name="update_action",columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean updateAction;

    @Column(name="list_action",columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean listAction;

   @Column(name="delete_action",columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean deleteAction;

   @JsonBackReference
   @ManyToOne
   @JoinColumn(name="role_id",nullable = false)
   private Roles roles;
}
