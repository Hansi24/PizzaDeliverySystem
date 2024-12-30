package com.pizzashop.chain;

import com.pizzashop.models.CustomPizza;


public interface PizzaCustomizationHandler {
    void setNext(PizzaCustomizationHandler nextHandler);

    void handleRequest(CustomPizza customPizza);
}
