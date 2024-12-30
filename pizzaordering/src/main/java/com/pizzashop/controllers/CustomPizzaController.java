package com.pizzashop.controllers;

import com.pizzashop.models.CustomPizza;
import com.pizzashop.models.Pizza;
import com.pizzashop.repositories.CustomPizzaRepository;
import com.pizzashop.repositories.PizzaRepository;
import com.pizzashop.services.CustomPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/custom-pizzas")
public class CustomPizzaController {

    @Autowired
    private CustomPizzaService customPizzaService;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private CustomPizzaRepository customPizzaRepository;

    /**
     * Get all custom pizzas.
     *
     * @return a list of all custom pizzas
     */
    @GetMapping
    public ResponseEntity<List<CustomPizza>> getAllCustomPizzas() {
        List<CustomPizza> customPizzas = customPizzaService.getAllCustomPizzas();
        if (customPizzas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customPizzas);
    }

    /**
     * Get a custom pizza by ID.
     *
     * @param id the ID of the custom pizza
     * @return the custom pizza if found, or NOT_FOUND if not
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomPizza> getCustomPizzaById(@PathVariable Long id) {
        return customPizzaService.getCustomPizzaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Create a new custom pizza.
     *
     * @param customPizza the custom pizza to be created
     * @return the created custom pizza, or BAD_REQUEST if input is invalid
     */
    @PostMapping("/create")
    public ResponseEntity<?> createCustomPizza(@RequestBody CustomPizza customPizza) {
        if (customPizza.getSelectedPizza() == null || customPizza.getSelectedPizza().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A valid selected pizza with an ID must be provided.");
        }

        Optional<Pizza> pizza = pizzaRepository.findById(customPizza.getSelectedPizza().getId());
        if (!pizza.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No pizza found with the provided selected pizza ID.");
        }

        // Set the pizzaName if not already set (in case of any issues with the builder)
        if (customPizza.getPizzaName() == null) {
            customPizza.setPizzaName(pizza.get().getName());
        }

        // Proceed with saving the custom pizza
        CustomPizza savedPizza = customPizzaRepository.save(customPizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPizza);
    }

    /**
     * Update an existing custom pizza.
     *
     * @param id               the ID of the custom pizza to update
     * @param customPizzaDetails the new details of the custom pizza
     * @return the updated custom pizza, or NOT_FOUND if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomPizza> updateCustomPizza(
            @PathVariable Long id,
            @RequestBody CustomPizza customPizzaDetails) {
        return customPizzaService.getCustomPizzaById(id)
                .map(existingPizza -> {
                    existingPizza.setCrust(customPizzaDetails.getCrust());
                    existingPizza.setSauce(customPizzaDetails.getSauce());
                    existingPizza.setCheese(customPizzaDetails.getCheese());
                    existingPizza.setToppings(customPizzaDetails.getToppings());
                    CustomPizza updatedPizza = customPizzaService.updateCustomPizza(existingPizza);
                    return updatedPizza;
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Delete a custom pizza by ID.
     *
     * @param id the ID of the custom pizza to delete
     * @return NO_CONTENT if successful, or NOT_FOUND if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomPizza(@PathVariable Long id) {
        if (customPizzaService.getCustomPizzaById(id).isPresent()) {
            customPizzaService.deleteCustomPizza(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error: Custom pizza with ID " + id + " not found.");
    }
}
