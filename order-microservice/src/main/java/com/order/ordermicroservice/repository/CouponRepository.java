package com.order.ordermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.ordermicroservice.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon,Long>{
    
}
