package com.pizzashop.services;

import com.pizzashop.models.CustomPizza;
import com.pizzashop.models.Pizza;
import com.pizzashop.repositories.CustomPizzaRepository;
import com.pizzashop.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomPizzaService {

    @Autowired
    private CustomPizzaRepository customPizzaRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    /**
     * Save a new CustomPizza with a reference to a selected Pizza.
     *
     * @param customPizza the custom pizza object to be saved
     * @return the saved CustomPizza object
     */
    public CustomPizza createCustomPizza(CustomPizza customPizza) {
        return customPizzaRepository.save(customPizza);
    }

    /**
     * Get a list of all CustomPizzas.
     *
     * @return a list of CustomPizza objects
     */
    public List<CustomPizza> getAllCustomPizzas() {
        return customPizzaRepository.findAll();
    }

    /**
     * Retrieve a CustomPizza by its ID.
     *
     * @param id the ID of the custom pizza
     * @return an Optional containing the CustomPizza if found, or empty if not
     */
    public Optional<CustomPizza> getCustomPizzaById(Long id) {
        return customPizzaRepository.findById(id);
    }

    /**
     * Delete a CustomPizza by its ID.
     *
     * @param id the ID of the custom pizza to be deleted
     */
    public void deleteCustomPizza(Long id) {
        if (customPizzaRepository.existsById(id)) {
            customPizzaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Custom Pizza not found with ID: " + id);
        }
    }

    /**
     * Update an existing CustomPizza.
     *
     * @param customPizza the updated CustomPizza object
     * @return the updated CustomPizza object
     */
    public CustomPizza updateCustomPizza(CustomPizza customPizza) {
        if (customPizzaRepository.existsById(customPizza.getId())) {
            return customPizzaRepository.save(customPizza);
        } else {
            throw new IllegalArgumentException("Custom Pizza not found with ID: " + customPizza.getId());
        }
    }


}
