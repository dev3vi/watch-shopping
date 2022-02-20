package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BrandDTO;
import com.example.entity.Brand;
import com.example.repository.BrandRepository;
import com.example.repository.ProductsRepository;
import com.example.service.BrandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{

	private final BrandRepository brandRepository;
	
	private final ProductsRepository productRepository;
	
	@Override
	public List<BrandDTO> getBrandDTO() {
		return this.brandRepository.findAll().stream().map(brand->
			BrandDTO.builder().id(brand.getId())
							  .brandName(brand.getBrandName())
							  .description(brand.getDescription())
							  .updateBy(brand.getUpdateBy())
							  .build())
							  .collect(Collectors.toList());
	}
	
	@Override
	public void createBrand(BrandDTO brandDTO, String username) {
		Brand brand = new Brand();		
		brand.setBrandName(brandDTO.getBrandName());
		if(!brandDTO.getDescription().trim().equals("")) {
			brand.setDescription(brandDTO.getDescription());
		}
		brand.setUpdateBy(username);	
		brandRepository.save(brand);

	}

	@Override
	public void deleteBrandById(Long id) {
		Brand brand = brandRepository.getById(id);
		brand.getProducts().forEach(pro->{
			System.out.println(pro.getId());
			productRepository.deleteById(pro.getId());
		});
		brandRepository.deleteById(id);
		
	}

}
