package com.pizzashop.response;

import com.pizzashop.state.OrderState;

public class PaymentWithOrderStatusResponse {
    private Long paymentId;
    private Double amount;
    private Boolean isPaid;
    private String paymentMethod;
    private Long orderId;
    private Long userId;
    private OrderState currentState;
    private String deliveryMethod;  // Added field for ConformOrder's deliveryMethod
    private Double totalPrice;      // Added field for ConformOrder's totalPrice

//    public PaymentWithOrderStatusResponse() {
//    }

    public PaymentWithOrderStatusResponse(Long paymentId, Double amount, Boolean isPaid, String paymentMethod, Long orderId, Long userId, OrderState currentState, String deliveryMethod, Double totalPrice) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.isPaid = isPaid;
        this.paymentMethod = paymentMethod;
        this.orderId = orderId;
        this.userId = userId;
        this.currentState = currentState;
        this.deliveryMethod = deliveryMethod;
        this.totalPrice = totalPrice;
    }


// Constructor


    // Getters and Setters
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(OrderState currentState) {
        this.currentState = currentState;
    }

//    public Boolean getPaid() {
//        return isPaid;
//    }
//
//    public void setPaid(Boolean paid) {
//        isPaid = paid;
//    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
