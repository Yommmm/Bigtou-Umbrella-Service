package com.bigtou.umbrella.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtou.umbrella.bean.UmbrellaOrder;

@Service
public class MachineService {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private HttpServletRequest request;

	public Object heartbeat(UmbrellaOrder params) {
		String machineId = params.getBeginMachineId();
		String machineIP = request.getRemoteAddr();
		String sjFlag = params.getSjFlag();
		
		UmbrellaOrder umbrellaOrder = orderService.queryOrderByMachineId(machineId);
		// 伞机状态：0 空闲，出伞状态 1 出伞，条件满足预出伞成功，写入订单
		if("0".equals(sjFlag) && "1".equals(umbrellaOrder.getCsFlag())) {
			umbrellaOrder.setMachineIP(machineIP);
			return orderService.save(umbrellaOrder);
		} else {
			Map<String, String> result = new HashMap<>();
			result.put("resultCode", "预出伞失败！");
			return result;
		}
	}
	
	public Object takeOutUmbrella(UmbrellaOrder params) {
		String machineId = params.getBeginMachineId();
		String sjFlag = params.getSjFlag();
		String umbrellaId = params.getUmbrellaRfid();
		UmbrellaOrder umbrellaOrder = orderService.queryOrderByMachineId(machineId);
		if(!"".equals(sjFlag)) {
			//sjFlag——1:出伞状态,2:出伞成功,3:出伞失败
			umbrellaOrder.setSjFlag(sjFlag);
			if(null != umbrellaId && !"".equals(umbrellaId)) {
				umbrellaOrder.setUmbrellaRfid(umbrellaId);
				umbrellaOrder.setCsFlag("0");
				umbrellaOrder.setBeginTime(new Date());
			}
			return orderService.save(umbrellaOrder);
		}
		Map<String, String> result = new HashMap<>();
		result.put("resultCode", "出伞失败！");
		return result;
	}
	
	public Object returnUmbrella(UmbrellaOrder params) {
		String umbrellaId = params.getUmbrellaRfid();
		UmbrellaOrder order = orderService.queryOrderByUmbrellaId(umbrellaId);
		order.setEndTime(new Date());
		order.setEndMachineId(params.getEndMachineId());
		return orderService.save(order);
	}
	
}
