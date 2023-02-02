package krugers.microservicio.auth.authmicroservice.client.coupon;

import org.springframework.cloud.openfeign.FeignClient;
import krugers.microservicio.auth.authmicroservice.model.Coupon;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;

@FeignClient(name="coupon-microservice", path = "/api/coupons" )
public interface CouponClient {
    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@Valid @RequestBody Coupon coupon, BindingResult result);

    @GetMapping
    public ResponseEntity<List<Coupon>> listCoupons();

    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateCoupon(@PathVariable(name="id") Long id, @RequestBody Coupon coupon);
}
