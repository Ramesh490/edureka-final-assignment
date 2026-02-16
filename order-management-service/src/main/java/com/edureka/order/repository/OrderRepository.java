package com.edureka.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edureka.order.entity.Order;

public interface OrderRepository
extends JpaRepository<Order, Long> {
}

