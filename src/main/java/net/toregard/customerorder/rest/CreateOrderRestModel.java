package net.toregard.customerorder.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class CreateOrderRestModel {
    private String customerId;
    private String orderId;

    private List<OrderItem> orderItems;

}
