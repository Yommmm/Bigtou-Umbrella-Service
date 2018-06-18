package com.bigtou.umbrella.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtou.umbrella.bean.MachineUmbrellaNum;
import com.bigtou.umbrella.bean.MuHistory;
import com.bigtou.umbrella.dao.MuHistoryRepository;
import com.bigtou.umbrella.dao.UmbrellaMachineRepository;
import com.bigtou.umbrella.util.GlobalConstants;
import com.bigtou.umbrella.vo.Umbrella2Machine;
import com.bigtou.umbrella.vo.UmbrellaTypeNum;

@Service
public class UmbrellaService {

	private static final Logger logger = LoggerFactory.getLogger(UmbrellaService.class);

	@Autowired
	private UmbrellaMachineRepository umbrellaMachineRepository;
	
	@Autowired
	private MuHistoryRepository muHistoryRepository;
	
	/**
	 * 保存机器-伞型-存放信息
	 * @param umbrella2Machine
	 * @return
	 */
	public Object addUmbrella2Machine(Umbrella2Machine umbrella2Machine) {
		List<UmbrellaTypeNum> umbrellaList = umbrella2Machine.getUmbrellaList();
		String machineId = umbrella2Machine.getMachineId();
		
		// 保存操作人员操作存放信息
		for(UmbrellaTypeNum umbrellaTypeNum : umbrellaList) {
			// 当前是否有存放信息，Y-更新，N-新增
			MachineUmbrellaNum machineUmbrellaNum = umbrellaMachineRepository.queryMachineUmbrellaNumByMachineIdAndUmbrellaType(machineId, umbrellaTypeNum.getUmbrellaType());
			if(null == machineUmbrellaNum) {
				machineUmbrellaNum = new MachineUmbrellaNum();
				machineUmbrellaNum.setMuId(UUID.randomUUID().toString());
			}
			machineUmbrellaNum.setMachineId(machineId);
			machineUmbrellaNum.setUmbrellaType(umbrellaTypeNum.getUmbrellaType());
			machineUmbrellaNum.setUmbrellaNum(umbrellaTypeNum.getUmbrellaNum() + machineUmbrellaNum.getUmbrellaNum());
			machineUmbrellaNum = umbrellaMachineRepository.save(machineUmbrellaNum);
			
			// 保存存放日志
			MuHistory muHistory = new MuHistory();
			muHistory.setMuHistoryId(UUID.randomUUID().toString());
			muHistory.setMachineId(machineId);
			muHistory.setUmbrellaType(machineUmbrellaNum.getMachineId());
			muHistory.setUmbrellaNum(machineUmbrellaNum.getUmbrellaNum());
			muHistory.setOperateType(GlobalConstants.OPERATE_TYPE_OPERATE);
			muHistory.setOperateDate(new Date());
			muHistoryRepository.save(muHistory);
		}
		
		return umbrellaMachineRepository.queryMachineUmbrellaNumByMachineId(machineId);
	}
	
	/**
	 * 根据机器ID，伞型查询当前机器伞型存放数据
	 * @param machineId
	 * @param umbrellaType
	 * @return
	 */
	public Object getMachineUmbrellaTypeNum(String machineId, String umbrellaType) {
		return umbrellaMachineRepository.queryMachineUmbrellaNumByMachineIdAndUmbrellaType(machineId, umbrellaType);
	}
	
}
