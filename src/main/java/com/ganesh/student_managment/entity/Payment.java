package com.ganesh.student_managment.entity;

import com.ganesh.student_managment.constants.PaymentMethods;
import com.ganesh.student_managment.constants.PaymentStatus;
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
@Table(name = "payment", indexes = {
        @Index(name = "created_by", columnList = "created_by"),
        @Index(name = "last_modified_by", columnList = "last_modified_by")})
public class Payment extends Auditable<UUID> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "payment_method",nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethods;

    @Column(name = "amount",length = 100,nullable = false)
    private double amount;

    @Column(name = "upi_id",length = 100,nullable = false)
    private UUID upiId;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "payment_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne(targetEntity = Ticket.class)
    @JoinColumn(name = "ticked_id",nullable = false)
    private Ticket ticket;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id",nullable = false)
    private Users users;

}
