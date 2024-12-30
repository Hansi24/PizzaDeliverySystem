package com.pizzashop.state;

import com.pizzashop.models.OrderStatus;

public class PlacedState implements OrderState {
    @Override
    public void updateStatus(OrderStatus orderStatus) {
        System.out.println("Order is pending. Pizza has not been delivered yet.");
    }
    @Override
    public void handleAction(OrderStatus orderStatus) {
        System.out.println("Order is ready for delivery. Dispatching to delivery person...");
        orderStatus.setCurrentState(new InPreparationState()); // Transition to In Progress
    }
    @Override
    public String toString() {
        return "PlacedState";
    }
}
