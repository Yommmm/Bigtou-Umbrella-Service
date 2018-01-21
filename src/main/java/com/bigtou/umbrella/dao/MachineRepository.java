package com.bigtou.umbrella.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigtou.umbrella.bean.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, String> {

}
