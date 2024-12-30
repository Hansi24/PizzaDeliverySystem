package com.pizzashop.strategy;

import com.pizzashop.models.ConformOrder;
import com.pizzashop.models.User;
import com.pizzashop.repositories.ConformOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PayPalPayment implements PaymentStrategy {
    @Autowired
    private ConformOrderRepository conformOrderRepository;

    @Override
    public boolean pay(double amount, User user, Long orderId) {
        ConformOrder conformOrder = conformOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + orderId));

        // Process payment and loyalty points as needed
        int pointsEarned = (int) (amount / 100);
        user.setPoints(user.getPoints() + pointsEarned);

        // Return true if payment is successful and amount matches
        return amount == conformOrder.getTotalPrice();
    }
}
