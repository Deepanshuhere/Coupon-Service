package com.monk.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "coupon")
public class Coupon {

	@Id
	private String id;
	private CouponType type;

	@JsonTypeInfo(
					 use = JsonTypeInfo.Id.NAME,
					 include = JsonTypeInfo.As.EXTERNAL_PROPERTY,  
					 property = "type"
				 )
	@JsonSubTypes({
					 @JsonSubTypes.Type(value = CartWiseCouponDetails.class, name = "CART_WISE"),
					 @JsonSubTypes.Type(value = ProductWiseCouponDetails.class, name = "PRODUCT_WISE"),
					 @JsonSubTypes.Type(value = BxGyCouponDetails.class, name = "BXGY")
				 })	
	private CouponDetails details;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime createdAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime expiresAt;
}
