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
	
	@Column(name = "CS_FLAG")
	private String CSFlag;

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
	
}
