package com.bigtou.umbrella.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
		try {
			logger.info("out params : {}", JSONObject.toJSON(params).toString());
			System.out.println("出伞参数：" + JSONObject.toJSON(params).toString() + " " + new Date());
			return machineService.takeOutUmbrella(params);
		} catch (Exception e) {
			logger.error("出伞失败！");
			logger.error(e.getMessage());
			Map<String, String> errorInfo = new HashMap<>();
			errorInfo.put("resultCode", "出伞失败！请确认订单信息及机器状态！");
			return errorInfo;
		}
	}
	
	/**
	 * 还伞接口
	 * params.sjFlag = 4 还伞成功
	 * params.sjFlag = 5 还伞失败
	 * @param params
	 * @return
	 */
	@PostMapping(value = "/returnUmbrella")
	public Object returnUmbrella(@RequestBody UmbrellaOrder params) {
		try{
			logger.info("umbrella params : {}", JSONObject.toJSON(params).toString());
			System.out.println("还伞参数：" + JSONObject.toJSON(params).toString() + " " + new Date());
			return machineService.returnUmbrella(params);
		} catch(Exception e) {
			logger.error("还伞失败！");
			logger.error(e.getMessage());
			Map<String, String> errorInfo = new HashMap<>();
			errorInfo.put("resultCode", "还伞失败！请确认订单信息及机器状态！");
			return errorInfo;
		}
	}
	
}
