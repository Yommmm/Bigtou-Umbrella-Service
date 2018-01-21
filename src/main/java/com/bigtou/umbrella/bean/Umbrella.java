package com.bigtou.umbrella.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "UMBRELLA")
public class Umbrella {

	@Id
	@Column(name = "UMBRELLA_ID")
	private String umbrellaId;
	
	@Column(name = "UMBRELLA_NAME")
	private String umbrellaName;
	
	@Column(name = "UMBRELLA_TYPE")
	private String umbrellaType;
	
	@Column(name = "CS_FLAG")
	private String CSFlag;
	
	@Column(name = "MACHINE_ID")
	private String machineId;

	public String getUmbrellaId() {
		return umbrellaId;
	}

	public void setUmbrellaId(String umbrellaId) {
		this.umbrellaId = umbrellaId;
	}

	public String getUmbrellaName() {
		return umbrellaName;
	}

	public void setUmbrellaName(String umbrellaName) {
		this.umbrellaName = umbrellaName;
	}

	public String getCSFlag() {
		return CSFlag;
	}

	public void setCSFlag(String cSFlag) {
		CSFlag = cSFlag;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getUmbrellaType() {
		return umbrellaType;
	}

	public void setUmbrellaType(String umbrellaType) {
		this.umbrellaType = umbrellaType;
	}
	
}
