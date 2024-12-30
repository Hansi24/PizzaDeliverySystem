package com.pizzashop.observers;

import com.pizzashop.models.OrderStatus;

public interface Observer {
    void update(OrderStatus status);
}
