package com.pizzashop.controllers;

import com.pizzashop.models.OrderStatus;
import com.pizzashop.services.OrderStatusService;
import com.pizzashop.state.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-status")
public class OrderStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    @PutMapping("/{id}/update-status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id) {
        try {
            orderStatusService.updateOrderStatus(id);
            return ResponseEntity.ok("Order status updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
