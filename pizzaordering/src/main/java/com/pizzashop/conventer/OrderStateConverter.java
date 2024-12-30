package com.pizzashop.conventer;

import com.pizzashop.state.*;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStateConverter implements AttributeConverter<OrderState, String> {

    @Override
    public String convertToDatabaseColumn(OrderState attribute) {
        // Store only the class name, e.g., "PlacedState"
        if (attribute != null) {
            return attribute.getClass().getSimpleName();
        }
        return null;
    }

    @Override
    public OrderState convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        // Map the class name to the actual state class
        switch (dbData) {
            case "PlacedState":
                return new PlacedState();
            case "InPreparationState":
                return new InPreparationState();
            case "CancelledState":
                return new CancelledState();
            case "OutForDeliveryState":
                return new OutForDeliveryState();
            default:
                throw new IllegalArgumentException("Unknown state: " + dbData);
        }
    }
}
