package com.pizzashop.strategy;

import com.pizzashop.models.ConformOrder;
import com.pizzashop.models.Order;
import com.pizzashop.models.Payment;
import com.pizzashop.models.User;
import com.pizzashop.repositories.ConformOrderRepository;
import com.pizzashop.repositories.OrderRepository;
import com.pizzashop.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment implements PaymentStrategy {
    private final ConformOrderRepository conformOrderRepository;

    @Autowired
    public CreditCardPayment(ConformOrderRepository conformOrderRepository) {
        this.conformOrderRepository = conformOrderRepository;
    }

    @Override
    public boolean pay(double amount, User user, Long orderId) {
        ConformOrder conformOrder = conformOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + orderId));
        return conformOrder.getTotalPrice() == amount;
    }
}


