package com.bigtou.umbrella.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigtou.umbrella.bean.MessageTemplate;

@Repository
public interface MsgTemplateRepository extends JpaRepository<MessageTemplate, String> {

	MessageTemplate queryMessageTemplateByMsgTemplateCode(String msgTemplateCode);
	
}
