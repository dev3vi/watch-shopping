package com.example.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImgDTO {

	private String names;
	
	private MultipartFile image;
}
