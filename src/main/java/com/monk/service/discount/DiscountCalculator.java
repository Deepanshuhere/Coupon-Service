package com.monk.service.discount;

import com.monk.dto.CartDTO;
import com.monk.entity.CouponDetails;

public interface DiscountCalculator {
	int calculateDiscount(CouponDetails details, CartDTO cart);
}
