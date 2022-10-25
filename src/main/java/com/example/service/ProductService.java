package com.example.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.example.dto.response.ProductsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.ProductRequest;
import com.example.entity.Products;
import com.example.dto.ProductDTO;
import com.example.dto.ProductDetail;

@Service
public interface ProductService {
	
	Optional<ProductDTO> getAllProductDto(Long id);

	void createProduct(HttpServletRequest httpServletRequest, ProductRequest request) throws IOException;

	ProductsResponse getAllProductDetail(Integer page);

	void updateProduct(HttpServletRequest httpServletRequest, ProductRequest request) throws IOException;

	void deleteProduct(Long id);

	String saveFile(MultipartFile img, HttpServletRequest httpServletRequest) throws IOException;

	ProductDetail getProdDetail(String slug);
	

}
