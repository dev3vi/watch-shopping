package com.example.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="order_info")
public class OrderInfo {
	
	@EmbeddedId
	private OrderInfoId id;
	
	private Long quantity;
	
	@Column(name="unit_price")
	private BigDecimal price;
}
