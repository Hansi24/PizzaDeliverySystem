package com.pizzashop.state;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pizzashop.models.OrderStatus;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlacedState.class, name = "PlacedState"),
        @JsonSubTypes.Type(value = InPreparationState.class, name = "InPreparationState"),
        @JsonSubTypes.Type(value = CancelledState.class, name = "CancelledState"),
        @JsonSubTypes.Type(value = OutForDeliveryState.class, name = "OutForDeliveryState")
})
public interface OrderState {
    void updateStatus(OrderStatus orderStatus);
    void handleAction(OrderStatus orderStatus);
}
