package com.pizzashop.repositories;

import com.pizzashop.models.CustomPizzaList;
import com.pizzashop.models.ConformOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ConformOrderRepository extends JpaRepository<ConformOrder, Long> {
    }
