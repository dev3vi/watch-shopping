package com.example.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductDTO {
	
//	private Long id;


	private String name;
	
	private BigDecimal price;
	
	private String image; 
	
	private String slug;
	
	private Long quantity;
	
	private String productType;
	
	private String description;
	
	private String gender;
	
	private String doChiuNuoc;

	private Long brands;

	private Long cLDay; 

	private Long cLMat;

	@Override
	public String toString() {
		return "Optional[Product(name=" + name + ", price=" + price + ", image=" + image + ", slug=" + slug + ", quantity="
				+ quantity + ", productType=" + productType + ", description=" + description + ", gender=" + gender
				+ ", doChiuNuoc=" + doChiuNuoc + ", brands=" + brands + ", cLDay=" + cLDay + ", cLMat=" + cLMat + ")]";
	}

	public ProductDTO(String name, BigDecimal price, String image, String slug, Long quantity,
			String productType, String description, String gender, String doChiuNuoc, Long brands, Long cLDay,
			Long cLMat) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
		this.slug = slug;
		this.quantity = quantity;
		this.productType = productType;
		this.description = description;
		this.gender = gender;
		this.doChiuNuoc = doChiuNuoc;
		this.brands = brands;
		this.cLDay = cLDay;
		this.cLMat = cLMat;
	}
	
}
