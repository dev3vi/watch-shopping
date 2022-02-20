package com.example.service;

import org.springframework.stereotype.Service;

import com.example.dto.OderInfo;

@Service
public interface OderService {


	void createOrders(OderInfo info);

	void createOrderInfo(OderInfo info, Long id);
}
