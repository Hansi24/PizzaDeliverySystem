package com.pizzashop.observer;

import com.pizzashop.models.Pizza;

public interface Observer {
    Long getId(); // Method to get the unique ID of the observer
    void update(Pizza pizza);
}
