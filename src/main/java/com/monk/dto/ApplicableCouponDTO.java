package com.monk.dto;

import com.monk.entity.CouponType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApplicableCouponDTO {

	private String couponId;
	private CouponType type;
	private int discount;
}
