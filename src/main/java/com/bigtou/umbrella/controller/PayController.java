package com.bigtou.umbrella.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigtou.umbrella.util.CommUtil;
import com.bigtou.umbrella.util.Constants;
import com.bigtou.umbrella.util.HttpUtil;
import com.bigtou.umbrella.util.QRCodeUtil;
import com.bigtou.umbrella.util.XMLUtil;

@RestController
@RequestMapping(value = "wechat")
public class PayController {

	private static final Logger logger = LoggerFactory.getLogger(PayController.class);
	
	@GetMapping(value = "/hello")
	public String test() {
		return "Hello";
	}
	
	@GetMapping(value = "pay")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("mch_id", Constants.MCH_ID);
			params.put("appid", Constants.APP_ID);
			params.put("notify_url", Constants.NOTIFY_URL);
			params.put("fee_type", Constants.FEE_TYPE);
			params.put("device_info", Constants.DEVICE_INFO);
			params.put("trade_type", Constants.TRADE_TYPE);
			params.put("sign_type", Constants.SIGN_TYPE);
			params.put("nonce_str", CommUtil.getNonce_str());
			params.put("out_trade_no", CommUtil.getNonce_str());
			params.put("spbill_create_ip", CommUtil.getIpAddress(request));
			params.put("body", "充值充值充值");
			params.put("product_id", "12");
			params.put("total_fee", "1");
			params.put("sign", CommUtil.generateSignature(params, Constants.API_KEY, "HMACSHA256"));
			String requestXML = XMLUtil.mapToXml(params);
			String resultXML = HttpUtil.postData(Constants.UFDODER_URL, requestXML);
			Map<String, String> result = XMLUtil.doXMLParse(resultXML);
			String codeURL = result.get("code_url");
			System.out.println("=============================");
			System.out.println(codeURL);
			System.out.println("=============================");
			// 生成验证码
			QRCodeUtil.createQRCode(response, codeURL);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}
