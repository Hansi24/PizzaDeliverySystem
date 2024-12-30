package com.pizzashop.decorator;

public class BasePizzaDecorator implements PizzaComponent {
    private final PizzaComponent pizza;

    public BasePizzaDecorator(PizzaComponent pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }
}
