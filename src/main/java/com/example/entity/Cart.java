package com.example.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "cart")
@Entity
@Data
public class Cart {
	
	@EmbeddedId
	private CartId id;
	
	private Long quantity;
	
	@ManyToOne
	@MapsId("product_id")
	@JoinColumn(name = "product_id")
	private Products products;

	@ManyToOne
	@MapsId("username")
	@JoinColumn(name = "username")
	private User user;
}
