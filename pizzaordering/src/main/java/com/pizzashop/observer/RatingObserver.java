package com.pizzashop.observer;

import com.pizzashop.models.Pizza;

public class RatingObserver implements Observer {

    private Long id;  // Unique identifier for each observer

    public RatingObserver(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void update(Pizza pizza) {
        // Your update logic when the pizza's state changes
        System.out.println("Updating observer with ID: " + id + " for pizza: " + pizza.getName());
    }
}
