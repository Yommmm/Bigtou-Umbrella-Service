package com.bigtou.umbrella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigtou.umbrella.bean.MachineUmbrellaNum;

@Repository
public interface UmbrellaMachineRepository extends JpaRepository<MachineUmbrellaNum, String> {

	MachineUmbrellaNum queryMachineUmbrellaNumByMachineIdAndUmbrellaType(String machineId, String umbrellaType);
	
	List<MachineUmbrellaNum> queryMachineUmbrellaNumByMachineId(String machineId);
	
}
