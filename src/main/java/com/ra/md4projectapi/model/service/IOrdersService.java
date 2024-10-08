package com.ra.md4projectapi.model.service;

import com.ra.md4projectapi.model.entity.Orders;

import java.util.List;

public interface IOrdersService {
    List<Orders> getOrders();
    Orders getOrdersById(Long id);
    List<Orders> findByStatus(String status);
    Orders changeStatus(Long id, String status);
}
