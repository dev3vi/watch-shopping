package com.example.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Image;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetail {
	
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
	private String brands;
	private String cLDay;
	private String cLMat;
	private List<Image> img;
}
