package net.toregard.customerorder.core.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.toregard.customerorder.command.rest.OrderItem;

import java.util.List;

@Data
public class OrderCreatedEvent {
    private String OrderId;
    private String customerId;
    private List<OrderItem> items;
}

