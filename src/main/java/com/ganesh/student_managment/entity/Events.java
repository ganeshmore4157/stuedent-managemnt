package com.ganesh.student_managment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ganesh.student_managment.constants.EventCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "events", indexes = {
        @Index(name = "created_by", columnList = "created_by"),
        @Index(name = "last_modified_by", columnList = "last_modified_by")})
public class Events extends Auditable<UUID> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "events_Id")
    private UUID eventsId;

    @Column(name = "Title")
    private String title;

    private  String description;

    private boolean available;

    private String organizer;
    @Temporal(TemporalType.DATE)
    @Column(name="start_date")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    private String venue;
    @Enumerated(EnumType.STRING)
    @Column(name = "eventCategory_type",length = 50,nullable = false)
    private EventCategory eventCategory;

    @Column(name = "price")
    private double price;

    @Column(name = "seats_available")
    private long seats;

    @JsonBackReference
    @ManyToOne(targetEntity = Roles.class)
    @JoinColumn(name="role_id",nullable = false)
    private Roles roles;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Ticket> tickets = new ArrayList<>();





}
