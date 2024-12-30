package com.pizzashop.controllers;

import com.pizzashop.models.OrderStatus;
import com.pizzashop.models.Payment;
import com.pizzashop.models.Order;
import com.pizzashop.repositories.ConformOrderRepository;
import com.pizzashop.repositories.OrderStatusRepository;
import com.pizzashop.response.PaymentOrderStatusResponse;
import com.pizzashop.response.PaymentWithOrderStatusResponse;
import com.pizzashop.services.PaymentContext;
import com.pizzashop.repositories.PaymentRepository;
import com.pizzashop.repositories.OrderRepository;
import com.pizzashop.services.PaymentService;
import com.pizzashop.state.PlacedState;
import com.pizzashop.strategy.CashOnDeliveryPayment;
import com.pizzashop.strategy.CreditCardPayment;
import com.pizzashop.strategy.DigitalWalletPaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private ConformOrderRepository conformOrderRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private PaymentContext paymentContext;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/make-payment")
    public ResponseEntity<?> makePayment(@RequestBody Payment request) {
        try {
            // Determine payment strategy based on payment method
            switch (request.getPaymentMethod().toLowerCase()) {
                case "cash on delivery":
                    paymentContext.setPaymentStrategy(new CashOnDeliveryPayment());
                    break;
                case "credit card":
                    paymentContext.setPaymentStrategy(new CreditCardPayment(conformOrderRepository));
                    break;
                case "bank transfer":
                    paymentContext.setPaymentStrategy(new DigitalWalletPaymentStrategy(conformOrderRepository));
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid payment method");
            }

            // Process payment and set the amount in the payment object
            boolean isPaid = paymentContext.pay(request.getAmount(), request.getUser(), request.getConformOrder().getId());

            // Set payment status
            request.setPaid(isPaid);

            // Save payment (including isPaid status)
            Payment payment = paymentRepository.save(request);

            // Create and save OrderStatus
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setPayment(payment); // Save paymentId
            orderStatus.setCurrentState(new PlacedState());
            orderStatus.setUser(request.getUser());
            orderStatusRepository.save(orderStatus);
            return ResponseEntity.ok(orderStatus);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }



    @GetMapping("/orders/{userId}")
    public List<OrderStatus> getPaymentsByUserId(@PathVariable Long userId) {
//        return paymentService.getPaymentOrderStatusesByUserId(userId);
        return orderStatusRepository.findByUserId(userId);
    }

    @GetMapping("/orders")
    public List<OrderStatus> getPaymentsByUserId() {
//        return paymentService.getPaymentOrderStatusesByUserId(userId);
        return orderStatusRepository.findAll();
    }
}
