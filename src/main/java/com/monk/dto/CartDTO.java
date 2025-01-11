package com.monk.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {
	
	private List<CartItem> items;
	private int totalPrice;
	private int totalDiscount;
	private int finalPrice;

}