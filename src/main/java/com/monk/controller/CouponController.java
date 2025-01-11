package com.monk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monk.dto.ApplicableCouponDTO;
import com.monk.dto.CartDTO;
import com.monk.entity.Coupon;
import com.monk.service.CouponService;

@RestController
@RequestMapping("/api/v1/coupons")
public class CouponController {

	@Autowired
	private CouponService couponService;

	@PostMapping
	public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
		Coupon createdCoupon = couponService.createCoupon(coupon);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCoupon);
	}

	@GetMapping
	public ResponseEntity<List<Coupon>> getAllActiveCoupons() {
		return ResponseEntity.ok(couponService.getAllActiveCoupons());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Coupon> getCouponById(@PathVariable String id) {
		return couponService.getCouponById(id)
				  			.map(ResponseEntity::ok)
				  			.orElse(ResponseEntity.notFound()
				  			.build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Coupon> updateCoupon(@PathVariable String id, @RequestBody Coupon couponDetails) {
		return ResponseEntity.ok(couponService.updateCoupon(id, couponDetails));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCoupon(@PathVariable String id) {
		couponService.deleteCoupon(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/applicable-coupons")
	public ResponseEntity<List<ApplicableCouponDTO>> getApplicableCoupons(@RequestBody CartDTO cart) {
		return ResponseEntity.ok(couponService.findApplicableCoupons(cart));
	}

	@PostMapping("/apply-coupon/{id}")
	public ResponseEntity<CartDTO> applyCoupon(@PathVariable String id, @RequestBody CartDTO cart) {
		return ResponseEntity.ok(couponService.applyCoupon(id, cart));
	}
}