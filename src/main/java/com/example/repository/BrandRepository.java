package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{

}
