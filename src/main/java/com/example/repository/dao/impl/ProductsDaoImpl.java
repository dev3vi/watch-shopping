package com.example.repository.dao.impl;

import com.example.dto.request.BaseRequest;
import com.example.dto.request.ProductFilterRequest;
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
    public List<Products> productFilter(ProductFilterRequest request) {
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
            sql.append("AND ").append(getSortBy(request.getSortBy())).append("= ").append(request.getSortType());
        }
        return (List<Products>) baseDaoService.getNativeQuery(sql.toString(), index);
    }

    private Integer getIndex(BaseRequest request) {
       return (request.getPage() - 1) * 12;
    }
    private String getSortBy(String sortBy) {
        if("brand".equals(sortBy)) {
            return "p.brand_id";
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


}
