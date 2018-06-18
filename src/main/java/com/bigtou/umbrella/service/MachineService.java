package com.bigtou.umbrella.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtou.umbrella.bean.MachineUmbrellaNum;
import com.bigtou.umbrella.bean.MuHistory;
import com.bigtou.umbrella.bean.UmbrellaOrder;
import com.bigtou.umbrella.dao.MuHistoryRepository;
import com.bigtou.umbrella.dao.UmbrellaMachineRepository;
import com.bigtou.umbrella.util.GlobalConstants;

@Service
public class MachineService {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UmbrellaMachineRepository umbrellaMachineRepository;
	
	@Autowired
	private MuHistoryRepository muHistoryRepository;
	
	public Object heartbeat(UmbrellaOrder params) {
		String machineId = params.getBeginMachineId();
		String machineIP = request.getRemoteAddr();
		String sjFlag = params.getSjFlag();
		
		UmbrellaOrder umbrellaOrder = orderService.queryOrderByMachineId(machineId);
		// 伞机状态：0 空闲，出伞状态 1 出伞，条件满足预出伞成功，写入订单
		if(null != umbrellaOrder && "0".equals(sjFlag) && "1".equals(umbrellaOrder.getCsFlag())) {
			umbrellaOrder.setMachineIP(machineIP);
			return orderService.save(umbrellaOrder);
		} else {
			return "0";
		}
	}
	
	public Object takeOutUmbrella(UmbrellaOrder params) {
		String machineId = params.getBeginMachineId();
		String sjFlag = params.getSjFlag();
		String umbrellaId = params.getUmbrellaId();
		UmbrellaOrder umbrellaOrder = orderService.queryOrderByMachineId(machineId);
		if(!"".equals(sjFlag)) {
			//sjFlag——1:出伞状态,2:出伞成功,3:出伞失败
			umbrellaOrder.setSjFlag(sjFlag);
			if(null != umbrellaId && !"".equals(umbrellaId)) {
				umbrellaOrder.setUmbrellaId(umbrellaId);
				umbrellaOrder.setCsFlag("0");
				umbrellaOrder.setBeginTime(new Date());
				if("3".equals(sjFlag)) {
					umbrellaOrder.setStatus("doing");
				} else {
					umbrellaOrder.setStatus("finish");
					// 操作库存信息
					MachineUmbrellaNum machineUmbrellaNum = umbrellaMachineRepository.queryMachineUmbrellaNumByMachineIdAndUmbrellaType(machineId, params.getUmbrellaType());
					if(null != machineUmbrellaNum && null != machineUmbrellaNum.getUmbrellaNum() && 0 < machineUmbrellaNum.getUmbrellaNum()) {
						// 出伞删减库存
						machineUmbrellaNum.setUmbrellaNum(machineUmbrellaNum.getUmbrellaNum() - 1);
						umbrellaMachineRepository.save(machineUmbrellaNum);
						
						// 记录库存日志
						MuHistory muHistory = new MuHistory();
						muHistory.setMuHistoryId(UUID.randomUUID().toString());
						muHistory.setMachineId(machineId);
						muHistory.setUmbrellaNum(machineUmbrellaNum.getUmbrellaNum());
						muHistory.setOperateType(GlobalConstants.OPERATE_TYPE_OUT);
						muHistory.setUmbrellaType(machineUmbrellaNum.getUmbrellaType());
						muHistory.setOperateDate(new Date());
						muHistoryRepository.save(muHistory);
					}
				}
			}
			umbrellaOrder = orderService.save(umbrellaOrder);
			System.out.println(umbrellaOrder.toString());
			return umbrellaOrder;
		}
		Map<String, String> result = new HashMap<>();
		result.put("resultCode", "伞机状态错误，出伞失败！");
		System.out.println("出伞失败！");
		return result;
	}
	
	public Object returnUmbrella(UmbrellaOrder params) {
		String umbrellaId = params.getUmbrellaId();
		String sjFlag = params.getSjFlag();
		UmbrellaOrder order = orderService.queryOrderByUmbrellaId(umbrellaId);
		if("4".equals(sjFlag)) {
			order.setStatus("return");
			order.setEndTime(new Date());
			order.setEndMachineId(params.getEndMachineId());
			MachineUmbrellaNum machineUmbrellaNum = umbrellaMachineRepository.queryMachineUmbrellaNumByMachineIdAndUmbrellaType(params.getEndMachineId(), params.getUmbrellaType());
			if(null != machineUmbrellaNum && null != machineUmbrellaNum.getUmbrellaNum() && 0 < machineUmbrellaNum.getUmbrellaNum()) {
				machineUmbrellaNum.setUmbrellaNum(machineUmbrellaNum.getUmbrellaNum() + 1);
				umbrellaMachineRepository.save(machineUmbrellaNum);
				
				// 记录库存日志
				MuHistory muHistory = new MuHistory();
				muHistory.setMuHistoryId(UUID.randomUUID().toString());
				muHistory.setMachineId(params.getEndMachineId());
				muHistory.setUmbrellaNum(machineUmbrellaNum.getUmbrellaNum());
				muHistory.setOperateType(GlobalConstants.OPERATE_TYPE_IN);
				muHistory.setUmbrellaType(machineUmbrellaNum.getUmbrellaType());
				muHistory.setOperateDate(new Date());
				muHistoryRepository.save(muHistory);
			}
		}
		return orderService.save(order);
	}
	
}
