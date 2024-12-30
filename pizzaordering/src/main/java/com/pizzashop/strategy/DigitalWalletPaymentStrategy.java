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
public class DigitalWalletPaymentStrategy implements PaymentStrategy {
    @Autowired
    private final ConformOrderRepository conformOrderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public DigitalWalletPaymentStrategy(ConformOrderRepository conformOrderRepository) {
        this.conformOrderRepository = conformOrderRepository;
    }

    @Override
    public boolean pay(double amount, User user, Long orderId) {
        System.out.println("Processing Digital Wallet payment for amount: " + amount);

        ConformOrder conformOrder = conformOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + orderId));
        return conformOrder.getTotalPrice() == amount;
//        // Save Payment details
//        Payment payment = new Payment();
//        payment.setPaymentMethod("DIGITAL_WALLET");
//        payment.setPaid(amount == conformOrder.getTotalPrice());
//        paymentRepository.save(payment);
//
//        // If payment is successful, create an Order
//        if (payment.isPaid()) {
//            Order order = new Order();
//            order.setPayment(payment);
//            //order.setCustomPizzas(conformOrder.getCustomPizzas()); // Assuming getter exists
//            orderRepository.save(order);
//        }
//
//        return payment.isPaid();
    }



}
