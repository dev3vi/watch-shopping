package com.example.repository.dao;

import com.example.dto.request.ProductFilterRequest;
import com.example.entity.Products;

import java.util.List;

public interface ProductsDao {
    List<Products> productFilter(ProductFilterRequest request);
}
