package com.monk.factory;

import com.monk.entity.CouponType;
import com.monk.service.discount.BxGyDiscountCalculator;
import com.monk.service.discount.CartWiseDiscountCalculator;
import com.monk.service.discount.DiscountCalculator;
import com.monk.service.discount.ProductWiseDiscountCalculator;

public class DiscountCalculatorFactory {

	public static DiscountCalculator getCalculator(CouponType type) {

		switch (type) {
		case CART_WISE:
			return new CartWiseDiscountCalculator();
		case PRODUCT_WISE:
			return new ProductWiseDiscountCalculator();
		case BXGY:
			return new BxGyDiscountCalculator();
		default:
			throw new IllegalArgumentException("Unknown coupon type: " + type);

		}
	}
}
