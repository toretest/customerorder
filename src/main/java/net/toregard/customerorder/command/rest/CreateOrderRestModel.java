package net.toregard.customerorder.command.rest;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class CreateOrderRestModel {
    @NotNull
    private String customerId;
    @NotNull
    private List<OrderItem> orderItems;
}
