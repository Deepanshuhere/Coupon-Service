package com.monk.service.discount;

import com.monk.dto.CartDTO;
import com.monk.entity.CartWiseCouponDetails;
import com.monk.entity.CouponDetails;

public class CartWiseDiscountCalculator implements DiscountCalculator {

	@Override
	public int calculateDiscount(CouponDetails couponDetails, CartDTO cart) {
		CartWiseCouponDetails cartWiseCouponDetails = (CartWiseCouponDetails) couponDetails;

		if (cartWiseCouponDetails == null) return 0;
		
		int cartTotal = cart.getItems()
							.stream()
							.mapToInt(item -> item.getPrice() * item.getQuantity())
							.sum();
		
		if (cartTotal < cartWiseCouponDetails.getThreshold()) return 0;
		
		return (cartTotal * cartWiseCouponDetails.getDiscount()) / 100;
	}

}
