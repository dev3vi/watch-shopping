package com.example.repository.dao;

import org.springframework.stereotype.Service;

import java.util.List;

public interface BaseDaoService {
    public List<?> getNativeQuery(String sql, Integer index);
    public Integer getCountNativeQuery(String sql);
}
