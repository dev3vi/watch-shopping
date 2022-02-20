package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.BrandDTO;
import com.example.entity.Brand;

@Service
public interface BrandService {

	List<BrandDTO> getBrandDTO();

	void createBrand(BrandDTO brandDTO, String username);

	void deleteBrandById(Long id);
}
