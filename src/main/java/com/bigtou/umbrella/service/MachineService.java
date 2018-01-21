package com.bigtou.umbrella.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtou.umbrella.bean.UmbrellaOrder;
import com.bigtou.umbrella.util.GlobalConstants;

@Service
public class MachineService {
	
	@Autowired
	private OrderService orderService;

	public UmbrellaOrder heartbeat(Map<String, String> params) {
		String machineId = params.get("machineId");
		String machineIP = params.get("machineIP");
		String sjFlag = params.get("SJFlag");
		
		UmbrellaOrder umbrellaOrder = orderService.queryOrderByMachineId(machineId);
		if(GlobalConstants.SJ_FLAG_0.equals(sjFlag) && GlobalConstants.CS_FLAG_1.equals(umbrellaOrder.getCSFlag())) {
			umbrellaOrder.setMachineIP(machineIP);
			return orderService.saveOrder(umbrellaOrder);
		} else {
			return null;
		}
	}
	
	public UmbrellaOrder takeOutUmbrella(Map<String, String> params) {
		String machineId = params.get("machineId");
		String machineIP = params.get("machineIP");
		String sjFlag = params.get("SJFlag");
		String umbrellaId = params.get("umbrellaId");
		UmbrellaOrder umbrellaOrder = orderService.queryOrderByMachineId(machineId);
		umbrellaOrder.setSJFlag(sjFlag);
		umbrellaOrder.setMachineIP(machineIP);
		if(null != umbrellaId && !"".equals(umbrellaId)) {
			umbrellaOrder.setUmbrellaId(umbrellaId);
		}
		return orderService.saveOrder(umbrellaOrder);
	}
}
