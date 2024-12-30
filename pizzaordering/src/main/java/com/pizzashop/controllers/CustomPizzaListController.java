package com.pizzashop.controllers;


import com.pizzashop.models.CustomPizzaList;
import com.pizzashop.repositories.CustomPizzaListRepository;
import com.pizzashop.repositories.CustomPizzaRepository;
import com.pizzashop.repositories.ConformOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/custom-pizzas-list")
@CrossOrigin(origins = "http://localhost:3000") // Adjust to your React app's URL
public class CustomPizzaListController {

    @Autowired
    private CustomPizzaListRepository customPizzaListRepository;



    @PostMapping
    public ResponseEntity<String> saveCustomPizzas(@RequestBody List<CustomPizzaList> pizzas) {
        customPizzaListRepository.saveAll(pizzas);
        return ResponseEntity.ok("Custom pizzas saved successfully!");
    }
}
