package com.example.repository.dao;

import com.example.dto.request.ProductFilterRequest;
import com.example.dto.response.ProductsResponse;
import com.example.entity.Products;

import java.util.List;

public interface ProductsDao {
    ProductsResponse productFilter(ProductFilterRequest request);
}
