package com.bigtou.umbrella.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtou.umbrella.bean.UmbrellaOrder;

@Service
public class MachineService {
	
	@Autowired
	private OrderService orderService;

	public Object heartbeat(Map<String, String> params) {
		String machineId = params.get("machineId");
		params.get("machineIP");
		params.get("SJFlag");
		
		UmbrellaOrder umbrellaOrder = orderService.queryOrderByMachineId(machineId);
		
		
		return null;
	}
}
