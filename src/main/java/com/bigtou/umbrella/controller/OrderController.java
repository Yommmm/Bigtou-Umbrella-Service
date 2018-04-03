package com.bigtou.umbrella.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bigtou.umbrella.bean.UmbrellaOrder;
import com.bigtou.umbrella.service.OrderService;

@RestController
@RequestMapping(value = "/bigtou/order")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 创建订单接口
	 * @param params
	 * @return
	 */
	@PostMapping
	public UmbrellaOrder saveOrder(@RequestBody UmbrellaOrder params) {
		logger.info("save order : {}", JSONObject.toJSON(params).toString());
		System.out.println("订单创建参数：" + JSONObject.toJSON(params).toString());
		return orderService.saveOrder(params);
	}
	
	@GetMapping("/queryAllOrder/{secret}")
	@ResponseBody
	public List<UmbrellaOrder> allOrder(@PathVariable("secret") String secret ) {
		if(secret.equalsIgnoreCase("qwertyuiop")) {
			return orderService.queryAll();
		} else {
			return null;
		}
	}
}
