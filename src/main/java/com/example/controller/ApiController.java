package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BrandDTO;
import com.example.dto.ItemRequest;
import com.example.dto.UserAndProduct;
import com.example.entity.ChatLieuDay;
import com.example.entity.Products;
import com.example.repository.BrandRepository;
import com.example.repository.ChatLieuDayRepository;
import com.example.repository.ProductsRepository;
import com.example.service.BrandService;
import com.example.service.ItemCartService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {
	
	private final ChatLieuDayRepository chatLieuDayRepository;
	
	private final ItemCartService itemCartService;
	
	@GetMapping("/get-cld")
	public Optional<ChatLieuDay> getCld(@RequestParam(value = "id", required = false) Long id ) {
		System.out.println(id);
		System.out.println(this.chatLieuDayRepository.findById(id));
		return this.chatLieuDayRepository.findById(id);
	}
	
	@GetMapping("/get-item-by-username")
	public List<Products> getItem(@RequestParam(value = "username", required = false) String username) {
		return itemCartService.getItem(username);
		
	}
	
	@PostMapping("/add-item-username")
	public void addItemUsername(@RequestBody ItemRequest itemRequest) {
		itemCartService.linkItemAndUser(itemRequest);
	}
}
