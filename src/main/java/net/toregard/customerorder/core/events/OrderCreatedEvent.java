package net.toregard.customerorder.core.events;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.toregard.customerorder.command.rest.OrderItem;
import java.util.List;
@Data
@NoArgsConstructor
public class OrderCreatedEvent {
    private String OrderId;
    private String customerId;
    private List<OrderItem> items;
}

