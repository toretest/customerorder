package net.toregard.customerorder.core.events;


import lombok.Data;
import net.toregard.customerorder.rest.OrderItem;

import java.util.List;

@Data
public class OrderCreatedEvent {
    private String OrderId;
    private String customerId;
    private List<OrderItem> items;
}
