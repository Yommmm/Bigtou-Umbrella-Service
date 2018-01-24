package com.bigtou.umbrella.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtou.umbrella.bean.UmbrellaOrder;

@Service
public class MachineService {
	
	@Autowired
	private OrderService orderService;

	public UmbrellaOrder heartbeat(UmbrellaOrder params) {
		String machineId = params.getBeginMachineId();
		String machineIP = params.getMachineIP();
		String sjFlag = params.getSjFlag();
		
		UmbrellaOrder umbrellaOrder = orderService.queryOrderByMachineId(machineId);
		if("0".equals(sjFlag) && "1".equals(umbrellaOrder.getCsFlag())) {
			umbrellaOrder.setMachineIP(machineIP);
			return orderService.save(umbrellaOrder);
		} else {
			return null;
		}
	}
	
	public UmbrellaOrder takeOutUmbrella(UmbrellaOrder params) {
		String machineId = params.getBeginMachineId();
		String sjFlag = params.getSjFlag();
		String umbrellaId = params.getUmbrellaId();
		UmbrellaOrder umbrellaOrder = orderService.queryOrderByMachineId(machineId);
		if("1".equals(sjFlag)) {
			umbrellaOrder.setSjFlag(sjFlag);
			if(null != umbrellaId && !"".equals(umbrellaId)) {
				umbrellaOrder.setUmbrellaId(umbrellaId);
			}
			return orderService.save(umbrellaOrder);
		}
		return null;
	}
}
