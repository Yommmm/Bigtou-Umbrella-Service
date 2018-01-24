package com.bigtou.umbrella.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtou.umbrella.bean.UmbrellaOrder;
import com.bigtou.umbrella.dao.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public UmbrellaOrder saveOrder(UmbrellaOrder params) {
		UmbrellaOrder order = new UmbrellaOrder();
		order.setOrderId(UUID.randomUUID().toString());
		order.setBeginMachineId(params.getBeginMachineId());
		order.setCsFlag(params.getCsFlag());
		order.setUmbrellaType(params.getUmbrellaType());
		order.setCreateTime(new Date());
		return orderRepository.save(order);
	}

	public UmbrellaOrder queryOrderByMachineId(String machineId) {
		return orderRepository.queryUmbrellaOrderByBeginMachineIdOrderByCreateTimeDesc(machineId).get(0);
	}
	
	public UmbrellaOrder save(UmbrellaOrder umbrellaOrder) {
		return orderRepository.save(umbrellaOrder);
	}
}
