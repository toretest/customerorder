package net.toregard.customerorder.commands;

import io.grpc.internal.ClientStream;
import lombok.Data;

import javax.validation.constraints.Max;
import java.util.List;
@Data
public class CreateOrderCommand {
    private String customerId;
    private String orderId;
    private List<String> items;

}
