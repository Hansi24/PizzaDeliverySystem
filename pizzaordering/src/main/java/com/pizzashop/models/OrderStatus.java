package com.pizzashop.models;

import com.pizzashop.conventer.OrderStateConverter;
import com.pizzashop.state.OrderState;
import com.pizzashop.state.PlacedState;
import jakarta.persistence.*;

@Entity
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = OrderStateConverter.class)
    private OrderState currentState; // The current state of the pizza delivery

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment; // Field for paymentId (if needed for payment tracking)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Default constructor
    public OrderStatus() {
        this.currentState = new PlacedState(); // Default to Pending state
    }

    // Constructor with parameters


    public OrderStatus(OrderState currentState, Payment payment, User user) {
        this.currentState = currentState;
        this.payment = payment;
        this.user = user;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(OrderState currentState) {
        this.currentState = currentState;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    // Method to update the status of the order
    public void updateStatus() {
        currentState.updateStatus(this);
    }

    // Method to handle actions for the current state
    public void handleAction() {
        currentState.handleAction(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
