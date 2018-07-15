package com.bigtou.umbrella.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bigtou.umbrella.bean.MessageTemplate;
import com.bigtou.umbrella.service.MsgTemplateService;

@RestController
@RequestMapping("/bigtou/message")
public class MsgTemplateController {

	private static final Logger logger = LoggerFactory.getLogger(MsgTemplateController.class);
	
	@Autowired
	private MsgTemplateService msgTemplateService;
	
	@PostMapping("/template")
	public Object saveMsgTemplate(@RequestBody MessageTemplate messageTemplate) {
		logger.info("消息数据：{}", JSONObject.toJSON(messageTemplate).toString());
		return msgTemplateService.saveMsgTemplate(messageTemplate);
	}
	
	@GetMapping("/template/{templateCode}")
	public Object queryMsgTemplate(@PathVariable("templateCode") String templateCode) {
		return msgTemplateService.queryMesgTempByCode(templateCode);
	}
	
}
