package com.order.ordermicroservice.model;

import java.util.Date;

import lombok.Data;
/**
 * This microservice was created by Kevin and David
 */
@Data
public class Category {

    private Long id;

    private String name;    

    private String description;

    private Date created;
}
