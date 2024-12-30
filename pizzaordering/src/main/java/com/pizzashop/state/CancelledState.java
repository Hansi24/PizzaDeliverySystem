package com.pizzashop.state;

import com.pizzashop.models.OrderStatus;

public class CancelledState implements OrderState{
    @Override
    public void updateStatus(OrderStatus orderStatus) {
        System.out.println("Order has been canceled. Pizza will not be delivered.");
    }

    @Override
    public void handleAction(OrderStatus orderStatus) {
        System.out.println("Order is canceled. No further delivery action will be taken.");
    }
    @Override
    public String toString() {
        return "CancelledState";
    }
}
