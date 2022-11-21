package com.example.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity 
@Data
@Table(name="product_img")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	@JsonIgnore
	private Products productId;
	 
	private String dataImg;
	
	@CreationTimestamp
	@JsonIgnore
	private LocalDateTime createAt;
	
	@UpdateTimestamp
	@JsonIgnore
	private LocalDateTime updateAt;

	public Image(Products e, String image) {
		this.productId = e;
		this.dataImg = image;
	}
	public Image() {
	}
}
