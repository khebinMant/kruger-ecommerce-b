package com.order.ordermicroservice.model;

import java.util.Date;

import lombok.Data;
/**
 * This microservice was created by Kevin and David
 */
@Data
public class Review {

    private Long id;

    private Long raiting;

    private String text;

    private Long productId;

    private Date created;
}
