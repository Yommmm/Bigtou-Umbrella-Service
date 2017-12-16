package com.bigtou.umbrella.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigtou.umbrella.util.CommUtil;
import com.bigtou.umbrella.util.Constants;
import com.bigtou.umbrella.util.HttpUtil;
import com.bigtou.umbrella.util.PayUtil;
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

	/**
	 * 微信支付二维码获取接口
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "pay")
	public void getQR(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			// 生成验证码
			QRCodeUtil.createQRCode(response, codeURL);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * ' 微信扫码付款回调接口
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/callback")
	public void callBack(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("====================call back success====================");

		StringBuffer sb = new StringBuffer();
		InputStream inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();

		Map<String, String> params = null;
		try {
			params = XMLUtil.doXMLParse(sb.toString());
		} catch (JDOMException e) {
			logger.error(e.getMessage());
		}

		String resXml = "";
		// 反馈给微信服务器,判断签名是否正确
//		if (CommUtil.isTenpaySign("UTF-8", params, Constants.API_KEY)) {
			// 支付成功
			if ("SUCCESS".equals((String) params.get("result_code"))) {

				// 开始执行自己的业务逻辑
				System.out.println("my logic start");
				System.out.println("request: " + request);
				System.out.println("my logic finish");
				// 结束执行自己的业务逻辑

				logger.info("pay success");
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

			} else {
				logger.info("支付失败,错误信息：" + params.get("err_code"));
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}

//		} else {
//			logger.info("签名验证错误");
//			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
//					+ "<return_msg><![CDATA[签名验证错误]]></return_msg>" + "</xml> ";
//		}

		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(resXml.getBytes());
		System.out.println(resXml);
		out.flush();
		out.close();
	}

	/**
	 * 微信退款接口
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/refund")
	public String wechatRefund(HttpServletResponse response, HttpServletRequest request)
			throws Exception {

		String currTime = PayUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = PayUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;

		SortedMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("appid", Constants.APP_ID);
		parameters.put("mch_id", Constants.MCH_ID);
		parameters.put("nonce_str", nonce_str);
		parameters.put("out_trade_no", "");
		parameters.put("out_refund_no", "" + strTime);
		parameters.put("total_fee", "");
		parameters.put("refund_fee", "");
		parameters.put("op_user_id", Constants.MCH_ID);
//		WechatPayService wechatPayService = new WechatPayService();

		Map map = wechatPayService.forRefund(parameters);
		if (map != null) {
			String return_code = (String) map.get("return_code");
			String result_code = (String) map.get("result_code");
			if (return_code.equals("SUCCESS") && result_code.equals("SUCCESS")) {
				// 退款成功
				return "退款成功";
			} else {
				return (String) map.get("err_code_des");
			}
		} else {
			return "未知的错误";
		}
	}*/

}
