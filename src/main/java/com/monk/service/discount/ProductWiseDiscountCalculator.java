package com.monk.service.discount;

import com.monk.dto.CartDTO;
import com.monk.dto.CartItem;
import com.monk.entity.CouponDetails;
import com.monk.entity.ProductWiseCouponDetails;

public class ProductWiseDiscountCalculator implements DiscountCalculator {

	@Override
	public int calculateDiscount(CouponDetails couponDetails, CartDTO cart) {

		ProductWiseCouponDetails productWiseCouponDetails = (ProductWiseCouponDetails) couponDetails;

		if (productWiseCouponDetails == null) return 0;

		int totalDiscount = 0;

		for (CartItem item : cart.getItems()) {
			if (isProductApplicable(item.getProductId(), productWiseCouponDetails.getProductId())) {
				int itemTotalPrice = item.getPrice() * item.getQuantity();
				totalDiscount += (itemTotalPrice * productWiseCouponDetails.getDiscount()) / 100;
			}
		}

		return totalDiscount;
	}

	private boolean isProductApplicable(String cartProductId, String couponProductId) {
		return couponProductId.equalsIgnoreCase(cartProductId);
	}

}
