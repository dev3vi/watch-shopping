package com.example.dto.response;

import com.example.dto.ProductDetail;
import lombok.Data;

import java.util.List;

@Data
public class ProductsResponse {
    private Integer count;
    private Integer page;
    private List<ProductDetail> products;
}
