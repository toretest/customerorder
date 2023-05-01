package net.toregard.customerorder.core.events;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.toregard.customerorder.command.rest.OrderItem;

import java.util.List;

@Data
public class OrderUpdateEvent {
    private String OrderId;
    private String customerId;
    private List<OrderItem> items;
}
