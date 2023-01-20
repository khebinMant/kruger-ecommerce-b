package com.order.ordermicroservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * This microservice was created by Kevin and David
 */
@Data
@Entity
@Table(name="orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price")
    private Double totalPrice;

    @Temporal(TemporalType.DATE)
    private Date created;

    private String status;

    @Column(name="shipment_address")
    private String shipmentAddress; //cambiar por dirección mediante address_id

    //Column(name="cupon_id") //nullable true
    //private Long cuponId

    @Temporal(TemporalType.DATE)
    @Column(name="shipment_date")
    private Date shipmentDate;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

}
