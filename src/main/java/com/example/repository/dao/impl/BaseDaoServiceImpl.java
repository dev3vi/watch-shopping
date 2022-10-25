package com.example.repository.dao.impl;

import com.example.repository.dao.BaseDaoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class BaseDaoServiceImpl implements BaseDaoService {

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<?> getNativeQuery(String sql, Integer index) {
        List<?> resultList = entityManager.createQuery(sql)
                .setMaxResults(12)
                .setFirstResult(index)
                .getResultList();
        return resultList;
    }

    public Integer getCountNativeQuery(String sql) {
        List<?> resultList = entityManager.createQuery(sql)
                .getResultList();
        return resultList.size();
    }

    public List<?> getIdByName(String sql) {
        List<?> resultList = entityManager.createQuery(sql)
                .getResultList();
        return resultList;
    }

}
