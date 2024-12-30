package com.pizzashop.chain;

import com.pizzashop.models.CustomPizza;
import com.pizzashop.models.ConformOrder;
import com.pizzashop.models.Pizza;

import java.util.Collections;
import java.util.List;

public class ToppingHandler implements PizzaCustomizationHandler {
    private PizzaCustomizationHandler nextHandler;

    @Override
    public void setNext(PizzaCustomizationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(CustomPizza customPizza) {
        List<String> toppings = Collections.singletonList(customPizza.getToppings().toString());
        if (toppings == null || toppings.isEmpty()) {
            throw new IllegalArgumentException("At least one topping is required!");
        }
        System.out.println("Toppings validation passed: " + String.join(", ", toppings));
        if (nextHandler != null) nextHandler.handleRequest(customPizza);
    }
}
