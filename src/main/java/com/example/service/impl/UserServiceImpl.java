package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ItemRequest;
import com.example.dto.UserAndProduct;
import com.example.entity.Products;
import com.example.entity.User;
import com.example.repository.ProductsRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductsRepository productsRepository;
	
	@Override
	public void updateUser(ItemRequest itemRequest) {
//		User user = userRepository.getById(itemRequest.getUsername());
//		Products product = productsRepository.getById(itemRequest.getIdItem());
//		List<Products> prod = user.getProducts();
//		prod.add(product);
//		user.setProducts(prod);
//		userRepository.save(user);
		
	}

}
