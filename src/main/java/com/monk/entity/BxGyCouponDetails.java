package com.monk.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BxGyCouponDetails extends CouponDetails {

	private List<ProductQuantity> buyProducts;
	private List<ProductQuantity> getProducts;
	private int repetitionLimit;

}