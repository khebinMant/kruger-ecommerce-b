package com.order.ordermicroservice.services.coupon;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.ordermicroservice.entity.Coupon;
import com.order.ordermicroservice.entity.Status;
import com.order.ordermicroservice.repository.CouponRepository;

@Service
public class CouponServiceImpl implements ICouponService{

    @Autowired
    CouponRepository couponRepository;
    
    @Override
    public List<Coupon> findAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon createCoupon(Coupon coupon) {

        coupon.setCode(RandomStringUtils.random(8, "0123456789abcdef"));
        coupon.setCreated(new Date());
        coupon.setStatus(Status.NOT_USED);
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        Coupon couponDB = getCoupon(coupon.getId());
        if(couponDB == null){
            return null;
        }
        couponDB.setStatus(coupon.getStatus());
        return couponRepository.save(couponDB);
    }

    @Override
    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    @Override
    public Coupon getCoupon(Long id) {
        return couponRepository.findById(id).orElse(null);
    }

	@Override
	public Coupon getCouponByCode(String code) {
		Coupon c=couponRepository.findCouponByCode(code.toLowerCase());
		System.out.println(c);
		return c;
	}
    
}
