package com.bigtou.umbrella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigtou.umbrella.bean.UmbrellaOrder;

@Repository
public interface OrderRepository extends JpaRepository<UmbrellaOrder, Long> {

	List<UmbrellaOrder> queryUmbrellaOrderByBeginMachineIdOrderByCreateTimeDesc(String beginMachineId);
	
	List<UmbrellaOrder> queryUmbrellaOrderByUmbrellaRfidOrderByCreateTimeDesc(String umbrellaId);
}
