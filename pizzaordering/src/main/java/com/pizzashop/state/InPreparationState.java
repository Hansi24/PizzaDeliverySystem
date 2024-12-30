// src/main/java/com/pizzashop/state/InPreparationState.java
package com.pizzashop.state;

import com.pizzashop.models.Order;
import com.pizzashop.models.OrderStatus;

public class InPreparationState implements OrderState {

    @Override
    public void updateStatus(OrderStatus orderStatus) {
        System.out.println("Pizza is being delivered to the customer.");
    }

    @Override
    public void handleAction(OrderStatus orderStatus) {
        System.out.println("Pizza has been delivered.");
        orderStatus.setCurrentState(new OutForDeliveryState()); // Transition to Delivered
    }
    @Override
    public String toString() {
        return "InPreparationState";
    }
}
