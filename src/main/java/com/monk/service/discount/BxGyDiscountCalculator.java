package com.monk.service.discount;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.monk.dto.CartDTO;
import com.monk.dto.CartItem;
import com.monk.entity.BxGyCouponDetails;
import com.monk.entity.CouponDetails;
import com.monk.entity.ProductQuantity;

public class BxGyDiscountCalculator implements DiscountCalculator {

	@Override
	public int calculateDiscount(CouponDetails couponDetails, CartDTO cart) {

		BxGyCouponDetails bxGyCouponDetails = (BxGyCouponDetails) couponDetails;

		int totalDiscount = 0;

		Map<String, CartItem> cartItemMap = cart.getItems().stream()
				.collect(Collectors.toMap(CartItem::getProductId, item -> item));

		boolean isBuyProductsPresent = validateBuyProducts(cartItemMap, bxGyCouponDetails.getBuyProducts());

		if (!isBuyProductsPresent) 	return 0;

		int possibleRepetitions = calculatePossibleRepetitions(cartItemMap, bxGyCouponDetails.getBuyProducts());

		if (bxGyCouponDetails.getRepetitionLimit() > 0)
			possibleRepetitions = Math.min(possibleRepetitions, bxGyCouponDetails.getRepetitionLimit());

		for (int i = 0; i < possibleRepetitions; i++) {
			for (ProductQuantity getProduct : bxGyCouponDetails.getGetProducts()) {
				CartItem cartItem = cartItemMap.get(getProduct.getProductId());
				if (cartItem != null) {
					int discountForItem = calculateDiscountForItem(cartItem, getProduct.getQuantity());
					totalDiscount += discountForItem;
				}
			}
		}

		return totalDiscount;
	}

	private boolean validateBuyProducts(Map<String, CartItem> cartItemMap, List<ProductQuantity> buyProducts) {
		return buyProducts.stream()
						  .allMatch(buyProduct -> {
			CartItem cartItem = cartItemMap.get(buyProduct.getProductId());
			return cartItem != null && cartItem.getQuantity() >= buyProduct.getQuantity();
		});
	}

	private int calculatePossibleRepetitions(Map<String, CartItem> cartItemMap, List<ProductQuantity> buyProducts) {
		return buyProducts.stream()
						  .map(buyProduct -> {
												CartItem cartItem = cartItemMap.get(buyProduct.getProductId());
												return cartItem.getQuantity() / buyProduct.getQuantity();
 											 })
						  .min(Integer::compareTo).orElse(0);
	}

	private int calculateDiscountForItem(CartItem cartItem, int quantityToDiscount) {
		return cartItem.getPrice() * quantityToDiscount;
	}

}
