package com.bigtou.umbrella.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "MACHINE")
public class Machine {

	@Id
	@Column(name = "MACHINE_ID")
	private String machineId;
	
	@Column(name = "MACHINE_NAME")
	private String machineName;
	
	@Column(name = "MACHINE_IP")
	private String machineIP;
	
	@Column(name = "MACHINE_ADDRESS")
	private String machineAddress;
	
	@Column(name = "SJ_FLAG")
	private String SJFlag;

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getMachineIP() {
		return machineIP;
	}

	public void setMachineIP(String machineIP) {
		this.machineIP = machineIP;
	}

	public String getMachineAddress() {
		return machineAddress;
	}

	public void setMachineAddress(String machineAddress) {
		this.machineAddress = machineAddress;
	}

	public String getSJFlag() {
		return SJFlag;
	}

	public void setSJFlag(String sJFlag) {
		SJFlag = sJFlag;
	}
	
}
