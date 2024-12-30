package com.pizzashop.chain;

import com.pizzashop.models.CustomPizza;
import com.pizzashop.models.ConformOrder;
import com.pizzashop.models.Pizza;

public class CheeseHandler implements PizzaCustomizationHandler {
    private PizzaCustomizationHandler nextHandler;

    @Override
    public void setNext(PizzaCustomizationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(CustomPizza customPizza) {
        if (customPizza.getCheese() == null || customPizza.getCheese().isEmpty()) {
            throw new IllegalArgumentException("Cheese is required!");
        }
        System.out.println("Cheese validation passed: " + customPizza.getCheese());
        if (nextHandler != null) nextHandler.handleRequest(customPizza);
    }
}
