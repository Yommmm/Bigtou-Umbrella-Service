package com.bigtou.umbrella.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigtou.umbrella.bean.Umbrella;

@Repository
public interface UmbrellaRepository extends JpaRepository<Umbrella, String> {

}
