package com.pizzashop.chain;

import com.pizzashop.models.CustomPizza;
import com.pizzashop.models.ConformOrder;
//import com.pizzashop.models.Pizza;

public class CrustHandler implements PizzaCustomizationHandler {
    private PizzaCustomizationHandler nextHandler;

    @Override
    public void setNext(PizzaCustomizationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(CustomPizza customPizza) {
        if (customPizza.getCrust() == null || customPizza.getCrust().isEmpty()) {
            throw new IllegalArgumentException("Crust is required!");
        }
        if (nextHandler != null) nextHandler.handleRequest(customPizza);
    }
}
