package com.bigtou.umbrella.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigtou.umbrella.service.MachineService;

@RestController
@RequestMapping(value = "/bigtou/machine")
public class MachineController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	private MachineService machineService;
	
	@PostMapping(value = "/heartbeat")
	public Object outUmbrella(Map<String, String> params) {
		logger.info("save order : {}", params);
		return machineService.heartbeat(params);
	}
}
