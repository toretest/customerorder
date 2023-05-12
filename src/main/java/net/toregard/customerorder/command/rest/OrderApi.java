package net.toregard.customerorder.command.rest;

import net.toregard.customerorder.command.CreateOrderCommand;
import net.toregard.customerorder.command.UpdateOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/orders")
public class OrderApi {
    private final CommandGateway commandGateway;
    Logger logger = Logger.getLogger("OrderApi");

    public OrderApi(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    /*
    http://localhost:8080/orders

    Example
    {
    "customerId": "5a572f48-68b1-4f94-a17a-0fac9de782c8",
    "items": [
        {
            "orderItemId": "1",
            "quantity": 11,
            "name": "item 1"
        },
        {
            "orderItemId": "2",
            "quantity": 22,
            "name": "item 2"
        }
    ]
   }
     */
    @PostMapping
    public String createOrder(@RequestBody CreateOrderRestModel createOrderRestModel) {
         createOrderRestModel.getOrderItems().forEach(data -> data.setOrderItemId(UUID.randomUUID().toString()));
        return commandGateway.sendAndWait(
                CreateOrderCommand.builder()
                        .orderId(UUID.randomUUID().toString())
                        .customerId(createOrderRestModel.getCustomerId())
                        .items(createOrderRestModel.getOrderItems())
                        .build()
        );
    }

     @PutMapping
    public String updateOrder(@RequestBody UpdateOrderRestModel updateOrderRestModel) {
         return commandGateway.sendAndWait(
                UpdateOrderCommand.builder()
                        .orderId(updateOrderRestModel.getOrderId())
                        .customerId(updateOrderRestModel.getCustomerId())
                        .items(updateOrderRestModel.getOrderItems())
                        .build()
        );
    }


    public List<String> getOrders() {
        return List.of("");
    }

    @GetMapping("/order")
    public CreateOrderRestModel getOrder() {
        ArrayList<OrderItem> items = new ArrayList<OrderItem>();
        items.add(new OrderItem("1", 11,"item 1"));
        items.add(new OrderItem("2", 22,"item 2"));
        return CreateOrderRestModel.builder()
                .customerId(UUID.randomUUID().toString())
                .orderItems(items)
                .build();
    }


}
