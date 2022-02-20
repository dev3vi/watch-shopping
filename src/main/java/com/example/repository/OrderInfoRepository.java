package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.OrderInfo;
import com.example.entity.OrderInfoId;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, OrderInfoId>{

}
