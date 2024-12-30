package com.pizzashop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "conform_order_id", referencedColumnName = "id")
    private ConformOrder conformOrder;

    @Column(nullable = false)
    private String orderDetails;

    public Order(Long id, User user, ConformOrder conformOrder, Payment payment, String orderDetails) {
        this.id = id;
        this.user = user;
        this.conformOrder = conformOrder;
        this.payment = payment;
        this.orderDetails = orderDetails;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public ConformOrder getConformOrder() {
        return conformOrder;
    }

    public void setConformOrder(ConformOrder conformOrder) {
        this.conformOrder = conformOrder;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }
}
