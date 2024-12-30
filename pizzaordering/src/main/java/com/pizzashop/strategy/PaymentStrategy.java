package com.pizzashop.strategy;

import com.pizzashop.models.User;

public interface PaymentStrategy {

    //boolean processPayment(double amount, User user, String orderId);

    boolean pay(double amount, User user, Long orderId);
}

