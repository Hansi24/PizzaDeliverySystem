package com.pizzashop.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "conform_orders")
public class ConformOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deliveryMethod;

    // Relationship with User (fetching user details)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key column for userId
    private User user;

    // Relationship with CustomPizza (fetching custom pizza details)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "conform_orders_id") // Foreign key in the custom_pizzas table
    private List<CustomPizzaList> customPizzas;

    // Additional fields for order details
    private double totalPrice;

    // Constructors, Getters, Setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CustomPizzaList> getCustomPizzas() {
        return customPizzas;
    }

    public void setCustomPizzas(List<CustomPizzaList> customPizzas) {
        this.customPizzas = customPizzas;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
