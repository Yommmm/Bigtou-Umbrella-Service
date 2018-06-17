package com.bigtou.umbrella.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 机器-伞型-数量存放表
 * @author Yom
 *
 */
@Entity(name = "MACHINE_UMBRELLA_NUM")
public class MachineUmbrellaNum {

	@Id
	@Column(name = "M_U_ID")
	private String muId;
	
	@Column(name = "MACHINE_ID")
	private String machineId;
	
	@Column(name = "UMBRELLA_TYPE")
	private String umbrellaType;
	
	@Column(name = "MUID")
	private Double umbrellaNum;

	public String getMuId() {
		return muId;
	}

	public void setMuId(String muId) {
		this.muId = muId;
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

	public Double getUmbrellaNum() {
		return umbrellaNum;
	}

	public void setUmbrellaNum(Double umbrellaNum) {
		this.umbrellaNum = umbrellaNum;
	}
	
}
