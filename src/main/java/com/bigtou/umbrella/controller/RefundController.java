package com.bigtou.umbrella.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigtou.umbrella.service.WeChatService;
import com.bigtou.umbrella.vo.UmbrellaVO;

@RestController
@RequestMapping(value = "wechat")
public class RefundController {

	@Autowired
	private WeChatService wechatService;
	
	@PostMapping(value = "/refund")
	public String returMoney(UmbrellaVO umbrella) throws UnsupportedEncodingException {
		// 根据订单号查出金额和积分
		String refund = wechatService.refund(umbrella);
		return refund;
	}
}
