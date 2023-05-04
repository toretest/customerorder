package net.toregard.customerorder.query.rest;

import lombok.Data;
import net.toregard.customerorder.command.rest.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRestModel {
    private String orderId;
    private String customerId;
    private List<OrderItem> orderItems = new ArrayList<>();
}
