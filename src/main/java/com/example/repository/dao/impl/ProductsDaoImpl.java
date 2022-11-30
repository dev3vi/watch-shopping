package com.example.repository.dao.impl;

import com.example.dto.ProductDetail;
import com.example.dto.request.BaseRequest;
import com.example.dto.request.ProductFilterRequest;
import com.example.dto.response.ProductsResponse;
import com.example.entity.Products;
import com.example.repository.dao.ProductsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsDaoImpl implements ProductsDao {
    @Autowired
    private BaseDaoServiceImpl baseDaoService;

    @Override
    public ProductsResponse productFilter(ProductFilterRequest request) {
        ProductsResponse productsResponse = new ProductsResponse();
        Integer index = getIndex(request);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p FROM Products p WHERE 1 = 1 ");
        if(request.getBrand() != null) {
            sql.append("AND p.brands = ");
            sql.append(getIdByName("brand", request.getBrand()) + " ");
        }
        if(request.getGender() != null) {
            sql.append("AND p.gender = '" + request.getGender() + "' ");
        }
        if (request.getSortBy() != null) {
            sql.append(getSortBy(request.getSortBy())).append(request.getSortType());
        }
        List<ProductDetail> productDetails = (List<ProductDetail>) baseDaoService.getNativeQuery(sql.toString(), index);
        Integer count = baseDaoService.getCountNativeQuery(sql.toString());
        productsResponse.setCount(count);
        productsResponse.setPage(request.getPage());
        productsResponse.setProducts(productDetails);
        return productsResponse;
    }

    private Integer getIndex(BaseRequest request) {
       return (request.getPage() - 1) * 12;
    }
    private String getSortBy(String sortBy) {
        if("price".equals(sortBy)) {
            return "ORDER BY price ";
        }
        return null;
    }

    private String getIdByName(String key, String value) {
        if (key.equals("brand")) {
            String sql = String.format("SELECT b.id FROM Brand b WHERE b.brandName  = '%s'", value);
            return baseDaoService.getIdByName(sql).get(0).toString();
        }
        return null;
    }

//    @Override
//    public Products getProductByKey(String key) {
//        if (key == null) {
//            return null;
//        }
//        Integer index = getIndex(request);
//        String sql = String.format("SELECT p FROM Product p WHERE p.slug like '%" + key + "'");
//        List<ProductDetail> productDetails = (List<ProductDetail>) baseDaoService.getNativeQuery(sql.toString(), index);
//    }


}
