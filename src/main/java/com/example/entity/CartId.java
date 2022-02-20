package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Embeddable
@Data
public class CartId implements Serializable{

	@Column(name="username")
	private String username;
	
	@Column(name="product_id")
	private Long productId; 
	
	
}
