package net.toregard.customerorder.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {
    String orderItemId;
    Integer quantity;
}
