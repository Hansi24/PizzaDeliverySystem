//package com.pizzashop.builder;
//
//import com.pizzashop.models.CustomPizza;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PizzaBuilder {
//    private String crust;
//    private String sauce;
//    private String cheese;
//    private List<String> toppings = new ArrayList<>();
//
//    public PizzaBuilder setCrust(String crust) {
//        this.crust = crust;
//        return this;
//    }
//
//    public PizzaBuilder setSauce(String sauce) {
//        this.sauce = sauce;
//        return this;
//    }
//
//    public PizzaBuilder setCheese(String cheese) {
//        this.cheese = cheese;
//        return this;
//    }
//
//    public PizzaBuilder addTopping(String topping) {
//        this.toppings.add(topping);
//        return this;
//    }
//
//    public CustomPizza build() {
//        CustomPizza pizza = new CustomPizza(CustomPizza.Builder);
//        pizza.setCrust(crust);
//        pizza.setSauce(sauce);
//        pizza.setCheese(cheese);
//        pizza.setToppings(toppings.toString());
//        return pizza;
//    }

