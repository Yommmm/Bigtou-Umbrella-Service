package com.bigtou.umbrella.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtou.umbrella.bean.UmbrellaOrder;
import com.bigtou.umbrella.dao.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public UmbrellaOrder saveOrder(UmbrellaOrder params) {
		String machineIP = request.getRemoteAddr();
		UmbrellaOrder order = new UmbrellaOrder();
		// 订单号
		order.setOrderId(UUID.randomUUID().toString());
		// 机器编号
		order.setBeginMachineId(params.getBeginMachineId());
		// 出伞状态
		order.setCsFlag(params.getCsFlag());
		// 出伞类型
		order.setUmbrellaType(params.getUmbrellaType());
		// IP
		order.setMachineIP(machineIP);
		order.setStatus("start");
		order.setCreateTime(new Date());
		return orderRepository.save(order);
	}

	public UmbrellaOrder queryOrderByMachineId(String machineId) {
		return orderRepository.queryUmbrellaOrderByBeginMachineIdAndCsFlagOrderByCreateTimeDesc(machineId, "1").get(0);
	}
	
	public UmbrellaOrder queryOrderByUmbrellaId(String umbrellaId) {
		return orderRepository.queryUmbrellaOrderByUmbrellaIdOrderByBeginTimeDesc(umbrellaId).get(0);
	}
	
	public UmbrellaOrder save(UmbrellaOrder umbrellaOrder) {
		return orderRepository.save(umbrellaOrder);
	}
	
	public List<UmbrellaOrder> queryAll() {
		return orderRepository.findAll();
	}
}
