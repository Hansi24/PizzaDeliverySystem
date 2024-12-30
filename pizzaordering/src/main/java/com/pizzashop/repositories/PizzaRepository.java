package com.pizzashop.repositories;

//import com.pizzashop.models.CustomPizza;
import com.pizzashop.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
