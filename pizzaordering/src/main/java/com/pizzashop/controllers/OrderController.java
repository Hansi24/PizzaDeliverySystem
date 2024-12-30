package com.pizzashop.controllers;

import com.pizzashop.models.ConformOrder;
import com.pizzashop.repositories.ConformOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conformorder")
public class OrderController {

    @Autowired
    private ConformOrderRepository conformOrderRepository;

    // Get all orders with user and custom pizza details
    @GetMapping
    public List<ConformOrder> getAllOrders() {
        return conformOrderRepository.findAll();
    }

    // Get order by ID with user and custom pizza details
    @GetMapping("/{id}")
    public Optional<ConformOrder> getOrderById(@PathVariable Long id) {
        return conformOrderRepository.findById(id);
    }

    // Create a new order (POST)
    @PostMapping
    public ConformOrder createOrder(@RequestBody ConformOrder order) {
        return conformOrderRepository.save(order);
    }
}
