package net.toregard.customerorder.command.rest;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateOrderRestModel {
    private String customerId;
    private List<OrderItem> orderItems;

}
