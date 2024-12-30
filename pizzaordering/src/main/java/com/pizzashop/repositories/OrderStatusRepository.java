package com.pizzashop.repositories;

import com.pizzashop.models.OrderStatus;
import com.pizzashop.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    List<OrderStatus> findByUserId(Long userId);
}
