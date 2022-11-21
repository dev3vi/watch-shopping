package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ItemRequest;
import com.example.dto.UserAndProduct;
import com.example.entity.Cart;
import com.example.entity.CartId;
import com.example.entity.Products;
import com.example.entity.User;
import com.example.repository.CartRepository;
import com.example.repository.ProductsRepository;
import com.example.repository.UserRepository;
import com.example.service.ItemCartService;

@Service
public class ItemCartServiceImpl implements ItemCartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Products> getItem(String username) {
        List<Products> prod = new ArrayList<>();
        List<Cart> cart = cartRepository.findByIdUsername(username);
        cart.forEach(e -> {
            prod.add(e.getProducts());
        });
        return prod;
    }

    @Override
    public void linkItemAndUser(ItemRequest itemRequest) {
        CartId cartId = new CartId();
        Cart cart = new Cart();
        User user = userRepository.getById(itemRequest.getUsername());
        cartId.setProductId(itemRequest.getIdItem());
//		cartId.setUsername(itemRequest.getUsername());		
        cart.setUser(user);
        cart.setId(cartId);
//		cart.setProducts(products);		
        cartRepository.save(cart);
    }

    @Override
    public void deleteItem(ItemRequest itemRequest) {
        CartId cartId = new CartId();
        cartId.setUsername(itemRequest.getUsername());
        cartId.setProductId(itemRequest.getIdItem());
        cartRepository.deleteById(cartId);
    }

}
