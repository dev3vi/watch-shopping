package com.example.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BrandDTO;
import com.example.service.BrandService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/brand")
@AllArgsConstructor
public class BrandController {
	
	private final BrandService brandService;
	
	@GetMapping("/all-brand")
	public List<BrandDTO> getAllBrand() {	
		System.out.println(this.brandService.getBrandDTO());
		return this.brandService.getBrandDTO();
	}
	
	@PostMapping("/creates-brand")
	public void createAllBrand(@RequestBody BrandDTO brandDTO) {	
	    this.brandService.createBrand(brandDTO,SecurityContextHolder.getContext().getAuthentication().getName().toString());
	}
	
	@DeleteMapping("/delete-brand")
	public void deleteBrand(Long id) {
		System.out.println(id);
		
		brandService.deleteBrandById(id);
		
	}
	
}
