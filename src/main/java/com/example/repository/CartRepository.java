package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Cart;
import com.example.entity.CartId;

public interface CartRepository extends JpaRepository<Cart, CartId>{

	List<Cart> findByIdUsername(String username);

//	@Query("select u from Cart u where u.id.username = :username")
//	Cart getCart(String username);
}
