package com.order.ordermicroservice.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String email;

    private String password;

    private String userName;

    private String firstName;

    private String lastName;

    private Date signDate;

    private Boolean verified;
    
    private Role role;

   private List<Cart> carts;
}
