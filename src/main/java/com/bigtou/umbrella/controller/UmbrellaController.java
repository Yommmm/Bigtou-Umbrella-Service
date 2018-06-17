package com.bigtou.umbrella.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.bigtou.umbrella.service.UmbrellaService;
import com.bigtou.umbrella.vo.Umbrella2Machine;

@RestController
@RequestMapping(value = "/bigtou/umbrella")
public class UmbrellaController {

	private static final Logger logger = LoggerFactory.getLogger(UmbrellaController.class);
	
	@Autowired
	private UmbrellaService umbrellaSetvice;
	
	/**
	 * 工作人员加伞接口
	 * @param umbrella2Machine
	 * @return
	 */
	@PostMapping(value = "/machineOperate")
	public Object addUmbrella2Machine(Umbrella2Machine umbrella2Machine) {
		logger.info("工作人员加伞接口， 操作数据：{}", JSON.toJSONString(umbrella2Machine).toString());
		return umbrellaSetvice.addUmbrella2Machine(umbrella2Machine);
	}
	
	/**
	 * 查询机器当前伞类型数量接口
	 * @param machineId
	 * @param umbrellaType
	 * @return
	 */
	@GetMapping(value = "/{machineId}/{umbrellaType}")
	public Object queryUmbrellaNum(String machineId, String umbrellaType) {
		logger.info("查询机器当前伞类型数量接口， 伞机ID：{}，伞型：{}", machineId, umbrellaType);
		return umbrellaSetvice.getMachineUmbrellaTypeNum(machineId, umbrellaType);
	}
	
}
