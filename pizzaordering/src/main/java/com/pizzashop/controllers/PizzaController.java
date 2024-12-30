package com.pizzashop.controllers;

import com.pizzashop.models.Pizza;
import com.pizzashop.observer.FeedbackObserver;
import com.pizzashop.observer.RatingObserver;
import com.pizzashop.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    // Endpoint to fetch all pizzas
    @GetMapping
    public List<Pizza> getPizzas() {
        return pizzaService.getAllPizzas();
    }

    // Endpoint to add a new pizza
    @PostMapping
    public Pizza addPizza(@RequestBody Pizza pizza) {
//        // Create observers for feedback and rating with unique Long IDs (not Strings)
//        RatingObserver observer1 = new RatingObserver(1L); // Unique ID for observer1
//        RatingObserver observer2 = new RatingObserver(2L); // Unique ID for observer2
//        FeedbackObserver feedbackObserver = new FeedbackObserver(3L); // Unique ID for feedback observer
//
//        // Add observers to the pizza object
//        pizza.addObserver(observer1.getId());
//        pizza.addObserver(observer2.getId());
//        pizza.addObserver(feedbackObserver.getId()); // Adding FeedbackObserver to notify feedback changes

        // Save pizza and return
        return pizzaService.savePizza(pizza);
    }

//    // Endpoint to add feedback and rating to a pizza
//    @PutMapping("/{pizzaId}/feedback")
//    public Pizza addFeedbackAndRating(
//            @PathVariable Long pizzaId,
//            @RequestParam String feedback,
//            @RequestParam int rating) {
//
//        Pizza pizza = pizzaService.getPizzaById(pizzaId);
//        if (pizza != null) {
//            // Add feedback and rating
//            pizza.addFeedbackAndRating(feedback, rating);
//            pizzaService.savePizza(pizza); // Save the updated pizza
//        }
//
//        return pizza;
//    }
}
