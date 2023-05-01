package net.toregard.customerorder.command.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {
    private String orderItemId;
    private Integer quantity;
    private String name;
}
