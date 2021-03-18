package com.sl.ms.ordermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sl.ms.ordermanagement.entity.Orders;

public interface OrderItemDao  extends JpaRepository<Orders, Integer>{

}
