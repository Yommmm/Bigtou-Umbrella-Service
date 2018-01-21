package com.bigtou.umbrella.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "ORDER")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId; 
	
	@Column(name = "UMBRELLA_ID")
	private String umbrellaId;
	
	@Column(name = "BEGIN_TIME")
	private Date beginTime;
	
	@Column(name = "END_TIME")
	private Date endTime;
	
	@Column(name = "BEGIN_MACHINE_ID")
	private String beginMachineId;
	
	@Column(name = "END_MACHINE_ID")
	private String endMachineId;
	
	@Column(name = "USER_ID")
	private String userId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getUmbrellaId() {
		return umbrellaId;
	}

	public void setUmbrellaId(String umbrellaId) {
		this.umbrellaId = umbrellaId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getBeginMachineId() {
		return beginMachineId;
	}

	public void setBeginMachineId(String beginMachineId) {
		this.beginMachineId = beginMachineId;
	}

	public String getEndMachineId() {
		return endMachineId;
	}

	public void setEndMachineId(String endMachineId) {
		this.endMachineId = endMachineId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
