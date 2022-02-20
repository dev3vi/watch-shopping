package com.example.service.impl;

import org.springframework.stereotype.Service;

import com.example.dto.OderInfo;
import com.example.entity.OrderInfo;
import com.example.entity.OrderInfoId;
import com.example.entity.Orders;
import com.example.repository.OrderInfoRepository;
import com.example.repository.OrderRepository;
import com.example.service.OderService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OderService{

	private final OrderRepository orderRepository;
	
	private final OrderInfoRepository orderInfoRepository;
	
	@Override
	public void createOrders(OderInfo info) {
		
		Orders order = new Orders();
		order.setUsername(info.getUsername());
		order.setAddress(info.getAddress());
		order.setFullname(info.getFullname());
		order.setPhone(info.getPhone());
		orderRepository.save(order);
		Orders od = orderRepository.findTopByOrderByIdDesc();
		this.createOrderInfo(info,od.getId());
	}

	@Override
	public void createOrderInfo(OderInfo info,Long id) {
		OrderInfoId orderInfoId = new OrderInfoId();
		info.getItem().forEach(item->{
			orderInfoId.setOrderId(id);
			orderInfoId.setProductId(item.getId());
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setId(orderInfoId);
			orderInfo.setQuantity(item.getQuantity());
			orderInfoRepository.save(orderInfo);
		});

	}
	
	

}
