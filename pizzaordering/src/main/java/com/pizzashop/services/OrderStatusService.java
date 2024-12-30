package com.pizzashop.services;

import com.pizzashop.models.OrderStatus;
import com.pizzashop.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;
    private final SimpMessagingTemplate messagingTemplate; // WebSocket template

    public OrderStatusService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void updateOrderStatus(Long id) throws Exception {
        // Fetch order status by ID
        OrderStatus orderStatus = orderStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderStatus not found"));

        // Update order status
        orderStatus.handleAction();
        orderStatusRepository.save(orderStatus); // Save updated status

        // Notify all subscribers about the update
        messagingTemplate.convertAndSend(
                "/topic/order-updates/" + orderStatus.getUser().getId(),
                "{\"orderId\": " + id + ", \"status\": \"" + orderStatus.getCurrentState().toString() + "\"}"
        );



        // Alternatively, notify specific user if needed (replace with the user's ID or session key)
        // messagingTemplate.convertAndSendToUser("user123", "/queue/order-updates", orderStatus);
    }

}
