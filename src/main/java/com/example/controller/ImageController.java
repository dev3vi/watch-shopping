package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.ImgDTO;
import com.example.dto.ImgResponse;
import com.example.service.ImageService;
import com.example.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ImageController {
	
	private final ImageService imageService;
	
	@PostMapping("/create-img")
	public void getProd(ImgDTO imgDTO,HttpServletRequest httpServletRequest) throws IOException {
		
		System.out.println(imgDTO);
		imageService.saveImg(imgDTO,httpServletRequest);

	}
	
	@PostMapping("/get-img")
	public List<ImgResponse> getImg() {
		return imageService.getImgAndName();
		 
	}
	
	@PostMapping("/clear-img-with-id")
	public void clearImg(Long id) {
		System.out.println(id);
		imageService.clearImg(id);
	}
}
