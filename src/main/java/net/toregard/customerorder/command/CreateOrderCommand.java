package net.toregard.customerorder.command;

import lombok.Builder;
import lombok.Data;
import net.toregard.customerorder.command.rest.OrderItem;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;
@Builder
@Data
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    private final String orderId;
    private final String customerId;
    private final List<OrderItem> items;

}
