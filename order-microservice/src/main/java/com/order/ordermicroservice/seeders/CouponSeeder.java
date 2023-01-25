package com.order.ordermicroservice.seeders;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.ordermicroservice.entity.Coupon;
import com.order.ordermicroservice.entity.Status;
import com.order.ordermicroservice.services.coupon.CouponServiceImpl;
import com.order.ordermicroservice.entity.Type;

@Component
public class CouponSeeder {

    @Autowired
    CouponServiceImpl couponServiceImpl;

    public void fillCouponsDB(){
        for (long i = 0; i < 50; i++) {
            Type type;
            if(i%2==0){
                type = Type.DIRECT;
            }
            else{
                type = Type.PERCENTAGE;
            } 
            couponServiceImpl.createCoupon(new Coupon(i, type, (int)(Math.random()*(50-20) +20) , RandomStringUtils.random(8, "0123456789abcdef"),Status.NOT_USED, new Date()));
        }
    }

}
