package net.toregard.customerorder.command;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateOrderItem {
    private String OrderItemId;
    private String orderId;
    private Integer quantity;
    private String name;
}
