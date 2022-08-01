package com.example.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Long>{

	@Query("SELECT COUNT(*) FROM Products")
	int getQuantity();

	
	@Query("SELECT name FROM Products")
	List<String> getName();

	Products findByName(String names);
	
	Products findTopByOrderByIdDesc(); 
	
	@Modifying
	@Transactional
	@Query("update Products prod set prod.slug= :Slug where prod.id = :id")
	void setSlug(String Slug, Long id);


	Products getBySlug(String slug);

	@Query("select prod from Products prod where prod.slug like %:slug%")
	List<Optional<Products>> getProdByKey(String slug);
}
