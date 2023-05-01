package net.toregard.customerorder.core.events;

import lombok.Data;

import javax.persistence.Id;

@Data
public class OrderItemCreatedEvent {
    private String OrderItemId;
    private String orderId;
    private Integer quantity;
    private String name;
}
