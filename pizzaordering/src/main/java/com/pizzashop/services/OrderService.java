package com.pizzashop.services;

import com.pizzashop.models.ConformOrder;
import com.pizzashop.repositories.ConformOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final ConformOrderRepository repository;

    public OrderService(ConformOrderRepository repository) {
        this.repository = repository;
    }

    public ConformOrder createOrder(ConformOrder order) {
        // Calculate total price (example: base price + customizations)
       // double totalPrice = order.getBasePrice() * order.getQuantity();
       // order.setTotalPrice(totalPrice);
        return repository.save(order);
    }

    public List<ConformOrder> getAllOrders() {
        return repository.findAll();
    }


}