package com.pizzashop.observer;

import com.pizzashop.models.Pizza;

public class FeedbackObserver implements Observer {

    private Long id; // Unique identifier for the observer

    public FeedbackObserver(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void update(Pizza pizza) {
        // Here, we could save feedback to the database or process it further
        System.out.println("New feedback for pizza " + pizza.getName() + ":");

    }
}
