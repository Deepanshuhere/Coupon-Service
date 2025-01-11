package com.monk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartWiseCouponDetails extends CouponDetails
{
	private int threshold;
	private int discount;
}
