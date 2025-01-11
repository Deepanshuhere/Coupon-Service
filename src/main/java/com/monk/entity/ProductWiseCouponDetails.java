package com.monk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWiseCouponDetails extends CouponDetails
{
	private String productId;
	private int discount;
}
