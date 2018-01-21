package com.bigtou.umbrella.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigtou.umbrella.bean.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
