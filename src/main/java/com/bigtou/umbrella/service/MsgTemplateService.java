package com.bigtou.umbrella.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigtou.umbrella.bean.MessageTemplate;
import com.bigtou.umbrella.dao.MsgTemplateRepository;

@Service
public class MsgTemplateService {
	
	private static final Logger logger = LoggerFactory.getLogger(MsgTemplateService.class);
	
	@Autowired
	private MsgTemplateRepository msgTemplateRepository;
	
	public Object saveMsgTemplate(MessageTemplate messageTemplate) {
		return msgTemplateRepository.save(messageTemplate);
	}
	
	public Object queryMesgTempByCode(String msgTemplateCode) {
		return msgTemplateRepository.queryMessageTemplateByMsgTemplateCode(msgTemplateCode);
	}
	
	
	
}
