package com.pizzashop.response;

import com.pizzashop.models.OrderStatus;
import com.pizzashop.models.Payment;

public class PaymentOrderStatusResponse {
    private Payment payment;
    private OrderStatus orderStatus;

    public PaymentOrderStatusResponse(Payment payment, OrderStatus orderStatus) {
        this.payment = payment;
        this.orderStatus = orderStatus;
    }

    // Getters and setters
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}

