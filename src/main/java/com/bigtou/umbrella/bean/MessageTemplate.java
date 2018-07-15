package com.bigtou.umbrella.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MessageTemplate {

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "MSG_TEMPLATE_ID")
	private String msgTemplateId;
	
	@Column(name = "MSG_TEMPLATE_CODE")
	@NotNull
	private String msgTemplateCode;
	
	@Column(name = "MSG_TEMPLATE_CONTENT")
	@NotNull
	private String msgTemplateContent;

	public String getMsgTemplateId() {
		return msgTemplateId;
	}

	public void setMsgTemplateId(String msgTemplateId) {
		this.msgTemplateId = msgTemplateId;
	}

	public String getMsgTemplateCode() {
		return msgTemplateCode;
	}

	public void setMsgTemplateCode(String msgTemplateCode) {
		this.msgTemplateCode = msgTemplateCode;
	}

	public String getMsgTemplateContent() {
		return msgTemplateContent;
	}

	public void setMsgTemplateContent(String msgTemplateContent) {
		this.msgTemplateContent = msgTemplateContent;
	}
	
}
