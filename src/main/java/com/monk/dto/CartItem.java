package com.monk.dto;

import lombok.Data;

@Data
public class CartItem {
	
	private String productId;
	private int quantity;
	private int price;
	
}