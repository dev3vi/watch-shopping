package com.example.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity 
@Data
@Table(name="brand")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String brandName;
	
	private String description;
	
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@CreationTimestamp
	private LocalDateTime updateAt;
	
	private String updateBy;
	
	@OneToMany(mappedBy = "brands", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Products> products;
}
