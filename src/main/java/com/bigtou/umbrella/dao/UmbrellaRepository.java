package com.bigtou.umbrella.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigtou.umbrella.bean.Umbrella;

public interface UmbrellaRepository extends JpaRepository<Umbrella, String> {

}
