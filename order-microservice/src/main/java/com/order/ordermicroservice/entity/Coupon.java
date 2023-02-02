package com.order.ordermicroservice.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="coupons")
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private Type type;

    private Integer quantity;

    @Column(name="code",nullable = false,unique = true)
    private String code;

    private Status status;

    @Column(name = "user_id")
    private Long userId;
    
    @Temporal(TemporalType.DATE)
    private Date created;
}



