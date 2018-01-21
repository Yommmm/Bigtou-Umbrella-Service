package com.bigtou.umbrella.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigtou.umbrella.bean.Machine;

public interface MachineRepository extends JpaRepository<Machine, String> {

}
