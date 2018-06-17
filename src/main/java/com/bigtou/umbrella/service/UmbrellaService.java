package com.bigtou.umbrella.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtou.umbrella.bean.MachineUmbrellaNum;
import com.bigtou.umbrella.dao.UmbrellaMachineRepository;
import com.bigtou.umbrella.vo.Umbrella2Machine;
import com.bigtou.umbrella.vo.UmbrellaTypeNum;

@Service
public class UmbrellaService {

	private static final Logger logger = LoggerFactory.getLogger(UmbrellaService.class);

	@Autowired
	private UmbrellaMachineRepository umbrellaMachineRepository;
	
	/**
	 * 保存机器-伞型-存放信息
	 * @param umbrella2Machine
	 * @return
	 */
	public Object addUmbrella2Machine(Umbrella2Machine umbrella2Machine) {
		List<UmbrellaTypeNum> umbrellaList = umbrella2Machine.getUmbrellaList();
		String machineId = umbrella2Machine.getMachineId();
		
		for(UmbrellaTypeNum umbrellaTypeNum : umbrellaList) {
			MachineUmbrellaNum machineUmbrellaNum = umbrellaMachineRepository.queryMachineUmbrellaNumByMachineIdAndUmbrellaType(machineId, umbrellaTypeNum.getUmbrellaType());
			if(null == machineUmbrellaNum) {
				machineUmbrellaNum = new MachineUmbrellaNum();
				machineUmbrellaNum.setMuId(UUID.randomUUID().toString());
			}
			machineUmbrellaNum.setMachineId(machineId);
			machineUmbrellaNum.setUmbrellaType(umbrellaTypeNum.getUmbrellaType());
			machineUmbrellaNum.setUmbrellaNum(umbrellaTypeNum.getUmbrellaNum());
			umbrellaMachineRepository.save(machineUmbrellaNum);
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
