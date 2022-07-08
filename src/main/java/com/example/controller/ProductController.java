package com.example.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.persistence.FetchType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BrandDTO;
import com.example.dto.ProductRequest;
import com.example.dto.ProductDTO;
import com.example.dto.ProductDetail;
import com.example.dto.ProductNameDTO;
import com.example.entity.Products;
import com.example.repository.BrandRepository;
import com.example.repository.ChatLieuDayRepository;
import com.example.repository.ProductsRepository;
import com.example.service.BrandService;
import com.example.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductController {

	private final ProductsRepository productsRepository;
	
	private final ProductService productService;
	

	
	@GetMapping("/get-prod-by-id") //~FetchType.EAGER
	public Optional<Products> getProd(@RequestParam(value = "id", required = false) Long id) {
		return this.productsRepository.findById(id);
	}
	
	@GetMapping("/get-prod-detail-by-id") //~FetchType.EAGER
	public ProductDetail getProdDetail(@RequestParam(value = "id", required = false) String id) {
		return productService.getProdDetail(id); 
	}
	
	@GetMapping("/get-quantity")
	public int getQuantity() {
		return this.productsRepository.getQuantity();
	}
	
	@GetMapping("/get-name")
	public List<String> getName() {
		return this.productsRepository.getName();
	}
	
	@GetMapping("/all-product-detail")
	public List<ProductDetail> getAll() {	
		return this.productService.getAllProductDetail();
	}
	
	@GetMapping("/get-productdto-by-id") 
	public Optional<ProductDTO> getAllDto(@RequestParam(value = "id", required = false) Long id) {	
		return this.productService.getAllProductDto(id) ;
	}
	
	@PostMapping("/create-product")
	public void createProduct(HttpServletRequest httpServletRequest,ProductRequest request) throws IOException {
		System.out.println(request);
		 this.productService.createProduct(httpServletRequest, request);
	}
	
	@PostMapping("/update-product")
	public void updateProduct(HttpServletRequest httpServletRequest,ProductRequest request) throws IOException {
		 this.productService.updateProduct(httpServletRequest, request);
	}
	
	@DeleteMapping("/delete-product")
	public void deleteProduct(Long id) throws IOException {
		System.out.println("delete"+id);
		this.productService.deleteProduct(id);
	}
}