package com.example.service;

import java.util.List;

import com.example.dto.ItemRequest;
import com.example.dto.UserAndProduct;
import com.example.entity.Products;

public interface ItemCartService {

	List<Products> getItem(String username);

	void linkItemAndUser(ItemRequest itemRequest);

	void deleteItem(ItemRequest itemRequest);

}
