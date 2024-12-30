package com.pizzashop.decorator;

public class ToppingsDecorator extends BasePizzaDecorator {
    private final String topping;

    public ToppingsDecorator(PizzaComponent pizza, String topping) {
        super(pizza);
        this.topping = topping;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", " + topping;
    }


    @Override
    public double getCost() {
        return super.getCost() + 0.75;
    }
}
