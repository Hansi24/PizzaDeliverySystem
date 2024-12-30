package com.pizzashop.services;

import com.pizzashop.models.User;
import com.pizzashop.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean pay(double amount, User user, Long orderId) {
        return paymentStrategy.pay(amount, user, orderId);
    }
}
