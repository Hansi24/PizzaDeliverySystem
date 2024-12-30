package com.pizzashop.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "custom_pizzas")
public class CustomPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String crust;
    private String sauce;
    private String cheese;
    private String additionalNotes;

    @ElementCollection
    private List<String> toppings;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza selectedPizza;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key for the user
    private User user;  // Reference to the User entity

    @Column(name = "pizza_name", nullable = false)
    private String pizzaName;

    private double pizzaPrice;

    // Private constructor to enforce use of Builder
    private CustomPizza(CustomPizzaBuilder builder) {
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.cheese = builder.cheese;
        this.toppings = builder.toppings;
        this.selectedPizza = builder.selectedPizza;
        this.pizzaName = builder.pizzaName;
        this.pizzaPrice = builder.pizzaPrice;
        this.additionalNotes = builder.additionalNotes;
        this.user = builder.user;  // Set the user
    }

    // Default constructor for JPA
    public CustomPizza() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getCrust() {
        return crust;
    }

    public String getSauce() {
        return sauce;
    }

    public String getCheese() {
        return cheese;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public Pizza getSelectedPizza() {
        return selectedPizza;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public User getUser() {
        return user;
    }

    // Setters for the new user field
    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public void setSelectedPizza(Pizza selectedPizza) {
        this.selectedPizza = selectedPizza;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    // Static inner Builder class
    public static class CustomPizzaBuilder {
        private String crust;
        private String sauce;
        private String cheese;
        private List<String> toppings;
        private Pizza selectedPizza;
        private String pizzaName;
        private double pizzaPrice;
        private String additionalNotes;
        private User user;  // Reference to User for Builder

        // Builder methods for setting the attributes
        public CustomPizzaBuilder setCrust(String crust) {
            this.crust = crust;
            return this;
        }

        public CustomPizzaBuilder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public CustomPizzaBuilder setCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public CustomPizzaBuilder setToppings(List<String> toppings) {
            this.toppings = toppings;
            return this;
        }

        // Validating and setting the selected pizza
        public CustomPizzaBuilder setSelectedPizza(Pizza selectedPizza) {
            if (selectedPizza == null || selectedPizza.getId() == null) {
                throw new IllegalArgumentException("Pizza must not be null, and Pizza ID must be provided");
            }
            this.selectedPizza = selectedPizza;
            this.pizzaName = selectedPizza.getName();
            this.pizzaPrice = selectedPizza.getPrice();
            return this;
        }

        // Set the user for the CustomPizza
        public CustomPizzaBuilder setUser(User user) {
            if (user == null || user.getId() == null) {
                throw new IllegalArgumentException("User must not be null, and User ID must be provided");
            }
            this.user = user;
            return this;
        }

        // Build method to create CustomPizza instance
        public CustomPizza build() {
            // You can add validation here to ensure that required fields are provided
            if (crust == null || sauce == null || cheese == null || toppings == null || toppings.isEmpty()) {
                throw new IllegalArgumentException("Crust, sauce, cheese, and toppings must be provided");
            }
            return new CustomPizza(this);
        }
    }
}
