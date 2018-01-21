package com.bigtou.umbrella.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigtou.umbrella.bean.UmbrellaOrder;

@Repository
public interface OrderRepository extends JpaRepository<UmbrellaOrder, Long> {

	UmbrellaOrder queryUmbrellaOrderByBeginMachineId(String beginMachineId);
}
