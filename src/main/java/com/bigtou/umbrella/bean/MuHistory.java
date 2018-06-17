package com.bigtou.umbrella.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 伞机历史表
 * @author Yom
 *
 */
@Entity(name = "M_U_HISTORY")
public class MuHistory {

	@Id
	@Column(name = "M_U_HISTORYID")
	private String muHistoryId;

	@Column(name = "MACHINE_ID")
	private String machineId;
	
	@Column(name = "UMBRELLA_TYPE")
	private String umbrellaType;
	
	@Column(name = "UMBRELLA_NUM")
	private Double umbrellaNum;
	
	/**
	 * 工作人员操作 | Operate
	 * 出伞                | Out
	 * 还伞                | In
	 */
	@Column(name = "OPERATE_TYPE")
	private String operateType;

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	public String getMuHistoryId() {
		return muHistoryId;
	}
	
	public void setMuHistoryId(String muHistoryId) {
		this.muHistoryId = muHistoryId;
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
