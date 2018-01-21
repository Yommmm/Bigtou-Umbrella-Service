package com.bigtou.umbrella.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtou.umbrella.bean.UmbrellaOrder;
import com.bigtou.umbrella.dao.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Object saveOrder(Map<String, String> params) {
		UmbrellaOrder order = new UmbrellaOrder();
		order.setOrderId(UUID.randomUUID().toString());
		order.setBeginMachineId(params.get("beginMachineId"));
		order.setCSFlag(params.get("CSFlag"));
		order.setUmbrellaType("umbrellaType");
		return orderRepository.save(order);
	}

	public UmbrellaOrder queryOrderByMachineId(String machineId) {
		return orderRepository.queryUmbrellaOrderByBeginMachineId(machineId);
	}
}
