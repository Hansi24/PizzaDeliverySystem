package com.pizzashop.observers;

import com.pizzashop.models.OrderStatus;

public class OrderStatusObserver implements Observer {
    private OrderStatus orderStatus;

    @Override
    public void update(OrderStatus status) {
        this.orderStatus = status;
        // Implement the notification logic based on order status
        System.out.println("Order status updated: " + orderStatus);
    }
}
