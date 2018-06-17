package com.bigtou.umbrella.vo;

import java.util.List;

public class Umbrella2Machine {

	private String machineId;
	
	private List<UmbrellaTypeNum> umbrellaList;

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public List<UmbrellaTypeNum> getUmbrellaList() {
		return umbrellaList;
	}

	public void setUmbrellaList(List<UmbrellaTypeNum> umbrellaList) {
		this.umbrellaList = umbrellaList;
	}
	
}
