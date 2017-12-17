package com.bigtou.umbrella.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bigtou.umbrella.util.CommUtil;
import com.bigtou.umbrella.util.Constants;
import com.bigtou.umbrella.util.GetWeChatOrderNo;
import com.bigtou.umbrella.util.HttpUtil;
import com.bigtou.umbrella.util.PayUtil;
import com.bigtou.umbrella.util.QRCodeUtil;
import com.bigtou.umbrella.util.RequestHandler;
import com.bigtou.umbrella.util.XMLUtil;
import com.bigtou.umbrella.vo.UmbrellaVO;

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
	@GetMapping(value = "pay/{productId}/{totalFee}")
	public void getQR(@PathVariable("productId") String productId, 
			@PathVariable("totalFee") String totalFee, 
			HttpServletRequest request, 
			HttpServletResponse response) {
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
			params.put("body", "大斗伞充值");
			params.put("product_id", productId);
			params.put("total_fee", totalFee);
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
	public void callBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		// 支付成功
		if ("SUCCESS".equals((String) params.get("result_code"))) {

			// 开始执行自己的业务逻辑
			System.out.println("my logic start");
			System.out.println("params: " + params);
			System.out.println("request: " + request);
			System.out.println("my logic finish");
			// 结束执行自己的业务逻辑

			logger.info("pay success");
			// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
			resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
					+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

		} else {
			logger.info("pay failed, message：" + params.get("err_code"));
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}

		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(resXml.getBytes());
		System.out.println(resXml);
		out.flush();
		out.close();
	}

	/**
	 * 微信退款接口
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/refundd")
	public String wechatRefund(HttpServletResponse response, HttpServletRequest request) throws Exception {
		String currTime = PayUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;

        String out_trade_no = "";
        String out_refund_no = "";
        String total_fee = "1";
        String refund_fee = "1";

        SortedMap<String, String> parameters = new TreeMap<String, String>();
        parameters.put("appid", Constants.APP_ID);
        parameters.put("mch_id", Constants.MCH_ID);
        parameters.put("nonce_str", nonce_str);
        // 在notify_url中解析微信返回的信息获取到 transaction_id，此项不是必填
        parameters.put("transaction_id", "微信支付订单中调用统一接口后微信返回的 transaction_id");
        parameters.put("out_trade_no", out_trade_no);
        // 自己设定的退款申请号，约束为UK
        parameters.put("out_refund_no", out_refund_no);
        parameters.put("total_fee", total_fee); //单位为分
        parameters.put("refund_fee", refund_fee); //单位为分
        // 操作员帐号, 默认为商户号
        parameters.put("op_user_id", Constants.MCH_ID);
        RequestHandler requestHandler = new RequestHandler(request, response);
        requestHandler.init(Constants.APP_ID, Constants.APP_SECRET, Constants.API_KEY);
        String sign = requestHandler.createSign(parameters);

        String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

        String xml = "<xml>"
                + "<appid><![CDATA[" + Constants.APP_ID + "]]></appid>"
                + "<mch_id><![CDATA[" + Constants.MCH_ID +"]]></mch_id>"
                + "<nonce_str><![CDATA[" + nonce_str + "]]></nonce_str>"
                + "<out_trade_no><![CDATA[" + out_trade_no + "]]></out_trade_no>"
                + "<out_refund_no><![CDATA[" + out_refund_no + "]]></out_refund_no>"
                + "<total_fee><![CDATA[" + total_fee + "]]></total_fee>"
                + "<refund_fee><![CDATA[" + refund_fee + "]]></refund_fee>"
                + "<op_user_id><![CDATA[" + Constants.MCH_ID + "]]></op_user_id>"
                + "<sign>" + sign + "</sign>"
                + "</xml>";

        try {
            Map map = GetWeChatOrderNo.forRefund(createOrderURL, xml);
            if(map != null){
                String return_code = (String) map.get("return_code");
                String result_code = (String) map.get("result_code");
                if(return_code.equals("SUCCESS") && result_code.equals("SUCCESS")){
                    System.out.println("退款成功");
                } else {
                    System.out.println("退款失败");
                }
            } else {
                System.out.println("退款失败");
            }
        } catch (Exception e) {
            System.out.print("退款失败");
            e.printStackTrace();
        }
        return null;
	}

}
