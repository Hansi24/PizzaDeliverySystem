package com.pizzashop.services;

import com.pizzashop.models.Pizza;
import com.pizzashop.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    // Get all pizzas
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    // Save a pizza (either new or updated)
    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    // Get a pizza by ID
    public Pizza getPizzaById(Long pizzaId) {
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
        return pizza.orElse(null); // Return null if not found
    }

//    // Add feedback and rating to a pizza and notify observers
//    public Pizza addFeedbackAndRating(Long pizzaId, String feedback, int rating) {
//        Pizza pizza = getPizzaById(pizzaId);
//        if (pizza != null) {
//            // Add feedback and rating to the pizza
//            pizza.addFeedbackAndRating(feedback, rating);
//
//            // Save the pizza and notify observers of the change
//            pizzaRepository.save(pizza);  // Save the updated pizza
//            pizza.notifyObservers((List<com.pizzashop.observer.Observer>) addFeedbackAndRating(pizzaId));      // Notify all observers of the change
//        }
//        return pizza;
//    }
//
//    private Object addFeedbackAndRating(Long pizzaId) {
//        return null;
//    }
}
