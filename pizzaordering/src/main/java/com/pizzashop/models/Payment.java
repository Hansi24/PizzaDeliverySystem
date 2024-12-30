package com.pizzashop.models;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "conformOrder_id", referencedColumnName = "id")
    private ConformOrder conformOrder;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private boolean isPaid;

    // Default constructor
    public Payment() {
    }

    // Constructor with all fields
    public Payment(Long id, Double amount, String paymentMethod, boolean isPaid, ConformOrder conformOrder) {
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.isPaid = isPaid;
        this.conformOrder = conformOrder;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public ConformOrder getConformOrder() {
        return conformOrder;
    }

    public void setConformOrder(ConformOrder conformOrder) {
        this.conformOrder = conformOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
