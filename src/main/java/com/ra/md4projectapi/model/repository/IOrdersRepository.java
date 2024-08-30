package com.ra.md4projectapi.model.repository;

import com.ra.md4projectapi.constants.Status;
import com.ra.md4projectapi.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByStatus(Status status);
}
