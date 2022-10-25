package com.example.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	private String image; 
	
	private String slug;
	
	private Long quantity;
	
	private String productType;
	
	private String description;
	
	private String gender;
	
	private String doChiuNuoc;
	
	private LocalDateTime createBy;
	
	private LocalDateTime updateBy;
	
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="brand_id")
	@JsonIgnore
	private Brand brands;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="chat_lieu_day_id")
	@JsonIgnore
	private ChatLieuDay cLDay;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="chat_lieu_mat_id")
	@JsonIgnore
	private ChatLieuMat cLMat;
 
	@OneToMany(mappedBy = "productId",fetch =  FetchType.LAZY)
	@JsonIgnore
	private List<Image> productImg;
	
//	@ManyToMany(mappedBy = "products")
//	private List<User> users;
}
