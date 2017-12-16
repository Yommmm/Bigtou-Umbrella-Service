package com.bigtou.umbrella.service;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.bigtou.umbrella.util.CommUtil;
import com.bigtou.umbrella.util.Constants;
import com.bigtou.umbrella.util.JSONUtil;
import com.bigtou.umbrella.util.XMLUtil;
import com.bigtou.umbrella.util.http.ClientCustomSSL;
import com.bigtou.umbrella.vo.UmbrellaVO;

public class WeChatService {

	private static final Logger logger = LoggerFactory.getLogger(WeChatService.class);

	public String refund(UmbrellaVO umbrellaVo) {

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", Constants.APP_ID);// 应用id
		packageParams.put("mch_id", Constants.MCH_ID);// 商户号
		packageParams.put("nonce_str", CommUtil.getNonce_str());// 随机字符串
		packageParams.put("out_trade_no", umbrellaVo.getOutRefundNo());// 订单号
		packageParams.put("out_refund_no", "refund" + CommUtil.getNonce_str());// 退款单号
		packageParams.put("total_fee", "1");// 订单总金额Utils.getMoney()
		packageParams.put("refund_fee", "1");// 退款总金额
		packageParams.put("op_user_id", Constants.MCH_ID);// 商户号

		String sign = CommUtil.signMd5(packageParams, Constants.API_KEY);
		String result = "FAIL";
		String msg = "";
		logger.debug("--sign--=" + sign);

		String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		String xml = null;
		try {
			xml = XMLUtil.createXML(packageParams, sign.toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("--xml-=" + xml);
		String retur = null;
		try {
			retur = ClientCustomSSL.doRefund(createOrderURL, xml);

			System.out.print(retur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!StringUtils.isEmpty(retur)) {
			Map map = JSONUtil.parseXmlToMap(retur);

			String returnCode = (String) map.get("return_code");
			if (returnCode.equals("SUCCESS")) {
				result = "SUCCESS";
				msg = "OK";
				int status = -1;
				String resultCode = (String) map.get("result_code");
				if (resultCode.equals("SUCCESS")) {
					status = 1;
				}
				if (status == 1) {
					// 订单号
					String outtradeno = (String) map.get("out_trade_no");
					// 业务操作
					// 业务操作
				}
			}
			if (result.equals("FAIL")) {
				msg = (String) map.get("return_msg");
				logger.info(" 微信退款失败 refundfail msg = {}", msg);
			}

		}
		return msg;
	}
}
