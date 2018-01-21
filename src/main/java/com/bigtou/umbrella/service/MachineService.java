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

	public UmbrellaOrder heartbeat(UmbrellaOrder params) {
		String machineId = params.getBeginMachineId();
		String machineIP = params.getMachineIP();
		String sjFlag = params.getSJFlag();
		
		UmbrellaOrder umbrellaOrder = orderService.queryOrderByMachineId(machineId);
		if(GlobalConstants.SJ_FLAG_0.equals(sjFlag) && GlobalConstants.CS_FLAG_1.equals(umbrellaOrder.getCSFlag())) {
			umbrellaOrder.setMachineIP(machineIP);
			return orderService.save(umbrellaOrder);
		} else {
			return null;
		}
	}
	
	public UmbrellaOrder takeOutUmbrella(UmbrellaOrder params) {
		String machineId = params.getBeginMachineId();
		String machineIP = params.getMachineIP();
		String sjFlag = params.getSJFlag();
		String umbrellaId = params.getUmbrellaId();
		UmbrellaOrder umbrellaOrder = orderService.queryOrderByMachineId(machineId);
		umbrellaOrder.setSJFlag(sjFlag);
		umbrellaOrder.setMachineIP(machineIP);
		if(null != umbrellaId && !"".equals(umbrellaId)) {
			umbrellaOrder.setUmbrellaId(umbrellaId);
		}
		return orderService.saveOrder(umbrellaOrder);
	}
}
