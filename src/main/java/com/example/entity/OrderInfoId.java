package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class OrderInfoId implements Serializable{


	@Column(name="product_id")
	private Long productId;
	
	@Column(name="order_id")
	private Long orderId; 
	
}
