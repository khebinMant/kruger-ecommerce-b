package com.order.ordermicroservice.services.coupon;

import java.util.List;

import com.order.ordermicroservice.entity.Coupon;

public interface ICouponService {

    public List<Coupon> findAllCoupons();

    public Coupon createCoupon(Coupon coupon);
    public Coupon updateCoupon(Coupon coupon);
    public void deleteCoupon(Long id);

    public Coupon getCoupon(Long id);
    
    public Coupon getCouponByCode (String code);

    public List<Coupon> findByUserId(Long id);
}
