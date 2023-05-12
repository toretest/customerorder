package net.toregard.customerorder.command.rest;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class UpdateOrderRestModel {
    @NotNull
    private String orderId;
    @NotNull
    private String customerId;
    @NotNull
    private List<OrderItem> orderItems;
}
