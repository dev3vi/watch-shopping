package com.example.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
	
	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	private MultipartFile image; 
	
	private String slug;
	
	private Long quantity;
	
	private String productType;
	
	private String description;
	
	private String gender;
	
	private String doChiuNuoc;

	private Long brands;

	private Long cLDay;

	private Long cLMat;

}
