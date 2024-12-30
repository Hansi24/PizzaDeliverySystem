package com.pizzashop.state;

import com.pizzashop.models.Order;
import com.pizzashop.models.OrderStatus;

public class OutForDeliveryState implements OrderState {
    @Override
    public void updateStatus(OrderStatus orderStatus) {
        System.out.println("Pizza has been successfully delivered to the customer.");
    }

    @Override
    public void handleAction(OrderStatus orderStatus) {
        System.out.println("Order completed. No further action needed.");
    }

    @Override
    public String toString() {
        return "OutForDeliveryState";
    }
}
