package com.bigtou.umbrella.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigtou.umbrella.service.OrderService;

@RestController
@RequestMapping(value = "/bigtou/order")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public Object saveOrder(Map<String, String> params) {
		logger.info("save order : {}", params);
		return orderService.saveOrder(params);
	}
}
