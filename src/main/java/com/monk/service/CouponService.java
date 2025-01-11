package com.monk.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.monk.dto.ApplicableCouponDTO;
import com.monk.dto.CartDTO;
import com.monk.entity.Coupon;
import com.monk.factory.DiscountCalculatorFactory;
import com.monk.repository.CouponRepository;
import com.monk.service.discount.DiscountCalculator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CouponService {

	private CouponRepository couponRepository;

	public Coupon createCoupon(Coupon coupon) {
		coupon.setCreatedAt(LocalDateTime.now());
		coupon.setExpiresAt(LocalDateTime.now().plusMonths(1));
		return couponRepository.save(coupon);
	}

	public List<Coupon> getAllActiveCoupons() {
		Optional<List<Coupon>> optionalCoupons = couponRepository.findByExpiresAtAfter(LocalDateTime.now());

		if (optionalCoupons.isEmpty())
			return new ArrayList<>();

		return optionalCoupons.get();
	}

	public Optional<Coupon> getCouponById(String id) {
		return couponRepository.findById(id);
	}

	public Coupon updateCoupon(String id, Coupon couponDetails) {
		Coupon existingCoupon = couponRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Coupon not found"));

		couponDetails.setId(id);
		couponDetails.setCreatedAt(existingCoupon.getCreatedAt());
		couponDetails.setExpiresAt(existingCoupon.getExpiresAt());
		return couponRepository.save(couponDetails);
	}

	public void deleteCoupon(String id) {
		Coupon coupon = couponRepository.findById(id).orElseThrow(() -> new RuntimeException("Coupon not found"));
		couponRepository.delete(coupon);
	}

	public List<ApplicableCouponDTO> findApplicableCoupons(CartDTO cart) {
		List<Coupon> activeCoupons = getAllActiveCoupons();
		List<ApplicableCouponDTO> applicableCoupons = new ArrayList<>();

		for (Coupon coupon : activeCoupons) {

			DiscountCalculator discountCalculator = DiscountCalculatorFactory.getCalculator(coupon.getType());
			int discount = discountCalculator.calculateDiscount(coupon.getDetails(), cart);
			if (discount >= 0) {
				ApplicableCouponDTO applicableCouponDTO = ApplicableCouponDTO.builder().couponId(coupon.getId())
						.type(coupon.getType()).discount(discount).build();
				applicableCoupons.add(applicableCouponDTO);
			}
		}

		return applicableCoupons;
	}

	public CartDTO applyCoupon(String couponId, CartDTO cart) {
		Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new RuntimeException("Coupon not found"));

		DiscountCalculator discountCalculator = DiscountCalculatorFactory.getCalculator(coupon.getType());
		int totalDiscount = discountCalculator.calculateDiscount(coupon.getDetails(), cart);
		
		int totalPrice = cart.getItems()
  							 .stream()
							 .mapToInt(item -> item.getPrice() * item.getQuantity())
							 .sum();
		
		cart.setTotalPrice(totalPrice);
		cart.setTotalDiscount(totalDiscount);
		cart.setFinalPrice(totalPrice - totalDiscount);
		
		return cart;
	}

}