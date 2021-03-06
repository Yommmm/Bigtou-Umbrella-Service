package com.bigtou.umbrella.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "UMBRELLA_ORDER")
public class UmbrellaOrder {

	@Id
	private String orderId; 
	
	@Column(name = "UMBRELLA_ID")
	private String umbrellaId;
	
	@Column(name = "UMBRELLA_TYPE")
	private String umbrellaType;
	
	@Column(name = "CS_FLAG")
	private String csFlag;
	
	/**
	 * 1:出伞状态
	 * 2:出伞成功
	 * 3:出伞失败
	 * 4:还伞成功
	 * 5:还伞失败
	 */
	@Column(name = "SJ_FLAG")
	private String sjFlag;
	
	@Column(name = "BEGIN_TIME")
	private Date beginTime;
	
	@Column(name = "END_TIME")
	private Date endTime;
	
	@Column(name = "BEGIN_MACHINE_ID")
	private String beginMachineId;
	
	@Column(name = "END_MACHINE_ID")
	private String endMachineId;
	
	@Column(name = "MACHINE_IP")
	private String machineIP;
	
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	/**
	 * start 订单开始
	 * doing 订单作业中
	 * finfish 订单处理完成
	 * return 订单已还伞
	 */
	@Column(name = "STATUS")
	private String status;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUmbrellaId() {
		return umbrellaId;
	}

	public void setUmbrellaId(String umbrellaId) {
		this.umbrellaId = umbrellaId;
	}

	public String getUmbrellaType() {
		return umbrellaType;
	}

	public void setUmbrellaType(String umbrellaType) {
		this.umbrellaType = umbrellaType;
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

	public String getCsFlag() {
		return csFlag;
	}

	public void setCsFlag(String csFlag) {
		this.csFlag = csFlag;
	}

	public String getSjFlag() {
		return sjFlag;
	}

	public void setSjFlag(String sjFlag) {
		this.sjFlag = sjFlag;
	}

	public String getMachineIP() {
		return machineIP;
	}

	public void setMachineIP(String machineIP) {
		this.machineIP = machineIP;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UmbrellaOrder [orderId=" + orderId + ", umbrellaId=" + umbrellaId + ", umbrellaType=" + umbrellaType
				+ ", csFlag=" + csFlag + ", sjFlag=" + sjFlag + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", beginMachineId=" + beginMachineId + ", endMachineId=" + endMachineId + ", machineIP=" + machineIP
				+ ", userId=" + userId + ", createTime=" + createTime + ", status=" + status + "]";
	}
	
}
