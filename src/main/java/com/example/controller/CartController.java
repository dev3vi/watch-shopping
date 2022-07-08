package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ItemRequest;
import com.example.dto.OderInfo;
import com.example.entity.Products;
import com.example.service.ItemCartService;
import com.example.service.OderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CartController {
	
	private final ItemCartService itemCartService;
	
	private final OderService oderService;
	
	@GetMapping("/get-item-by-username")
	public List<Products> getItem(@RequestParam(value = "username", required = false) String username) {
		return itemCartService.getItem(username);
		
	}
	
	@PostMapping("/add-item-username")
	public void addItemUsername(@RequestBody ItemRequest itemRequest) {
		itemCartService.linkItemAndUser(itemRequest); 
		
	}
	
	@DeleteMapping("/delete-item-by-username")
	public void deleteItem(@RequestBody ItemRequest itemRequest) {
		itemCartService.deleteItem(itemRequest);
	}
	
	@PostMapping("/get-oder-info")
	public void getOderInfo(@RequestBody OderInfo info) {
		oderService.createOrders(info);

	}
}
