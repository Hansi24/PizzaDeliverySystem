package com.pizzashop.repositories;

import com.pizzashop.models.CustomPizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomPizzaRepository extends JpaRepository<CustomPizza, Long> {

}
