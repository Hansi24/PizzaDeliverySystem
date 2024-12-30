package com.pizzashop.strategy;

import com.pizzashop.models.User;
import org.springframework.stereotype.Component;

@Component
public class CashOnDeliveryPayment implements PaymentStrategy {


    @Override
    public boolean pay(double amount, User user, Long orderId) {

        System.out.println("Processing Cash On Delivery payment for Order ID: " + orderId);
        return false;
    }
}
