package com.order.ordermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.order.ordermicroservice.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon,Long>{
    
	
	 @Query("FROM Coupon c WHERE lower(c.code)= :code")
		Coupon findCouponByCode(@Param("code") String code);
}
