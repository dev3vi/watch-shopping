package com.example.dto;

import com.example.entity.Brand;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandDTO {
	
	private Long id;
	
	private String brandName;
	
	private String description;
	
	private String updateBy;
	
//	 public BrandDTO conveter(Brand brand) { 
//		 return BrandDTO.builder().id(brand.getId())
//				 .brandName(brand.getBrandName())
//				 .description(brand.getDescription())
//				 .build();
//	 }
}
