package com.ganesh.student_managment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ganesh.student_managment.constants.TicketStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ticket_booking", indexes = {
        @Index(name = "created_by", columnList = "created_by"),
        @Index(name = "last_modified_by", columnList = "last_modified_by")})
public class Ticket extends Auditable<UUID> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name="ticked_id")
    private UUID tickedId;
    @Column(name = "full_name",length = 100,nullable = false)
    private String fullName;
    @Column(name = "mobile",length = 10)
    private String mobile;

    @Column(name = "email",length = 100,nullable = true)
    private String email;
    @Column(name="seat_number",nullable = false)
    private int seatNumber;

    @Column(name = "ticket_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "event_id", nullable = false)
    private Events event;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "user_id",nullable = false)
    private UUID userId;

}
