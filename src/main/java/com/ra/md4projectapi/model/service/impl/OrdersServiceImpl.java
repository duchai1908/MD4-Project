package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.constants.Status;
import com.ra.md4projectapi.model.entity.Orders;
import com.ra.md4projectapi.model.repository.IOrdersRepository;
import com.ra.md4projectapi.model.service.IOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements IOrdersService {
    private final IOrdersRepository ordersRepository;
    // Get All Orders
    @Override
    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    //Get OrderById
    @Override
    public Orders getOrdersById(Long id) {
        return ordersRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Orders Not Found"));
    }

    // Get All Orders By Status
    @Override
    public List<Orders> findByStatus(String status) {
        Status s;
        try {
             s = Status.valueOf(status);
        }catch (Exception e){
            throw new NoSuchElementException("Status Not Found");
        }
        return ordersRepository.findAllByStatus(s);
    }

    // Change status OrderById
    @Override
    public Orders changeStatus(Long id, String status) {
        Orders orders = ordersRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Orders Not Found"));
        Status s;
        try {
            s = Status.valueOf(status);
        }catch (Exception e){
            throw new NoSuchElementException("Status Not Found");
        }
        orders.setStatus(s);
        return ordersRepository.save(orders);
    }
}
