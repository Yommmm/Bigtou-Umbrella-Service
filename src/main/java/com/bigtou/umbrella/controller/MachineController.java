package com.bigtou.umbrella.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bigtou.umbrella.bean.UmbrellaOrder;
import com.bigtou.umbrella.service.MachineService;

@RestController
@RequestMapping(value = "/bigtou/machine")
public class MachineController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private MachineService machineService;
	
	/**
	 * 预出伞接口
	 * @param params
	 * @return
	 */
	@PostMapping(value = "/heartbeat")
	public Object heartBeat(@RequestBody UmbrellaOrder params) {
		logger.info("heartbeat : {}", JSONObject.toJSON(params).toString());
		return machineService.heartbeat(params);
	}
	
	/**
	 * 出伞接口
	 * @param params
	 * @return
	 */
	@PostMapping(value = "/takeOutUmbrella")
	public Object takeOutUmbrella(@RequestBody UmbrellaOrder params) {
		logger.info("out params : {}", JSONObject.toJSON(params).toString());
		System.out.println("出伞参数：" + JSONObject.toJSON(params).toString() + " " + new Date());
		return machineService.takeOutUmbrella(params);
	}
	
	@PostMapping(value = "/returnUmbrella")
	public Object returnUmbrella(@RequestBody UmbrellaOrder params) {
		logger.info("umbrella params : {}", JSONObject.toJSON(params).toString());
		System.out.println("还伞参数：" + JSONObject.toJSON(params).toString() + " " + new Date());
		return machineService.returnUmbrella(params);
	}
	
}
