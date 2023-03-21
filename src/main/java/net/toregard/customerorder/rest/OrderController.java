package net.toregard.customerorder.rest;

import net.toregard.customerorder.command.CreateOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final CommandGateway commandGateway;
    Logger logger = Logger.getLogger("OrderController");

    public OrderController(CommandGateway commandGateway) {
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
            "quantity": 11
        },
        {
            "orderItemId": "2",
            "quantity": 22
        }
    ]
   }
     */
    @PostMapping
    public String createOrder(@RequestBody CreateOrderRestModel createOrderRestModel) {
        logger.info(createOrderRestModel.toString());
        try {
            commandGateway.sendAndWait(
                    CreateOrderCommand.builder()
                            .OrderId(UUID.randomUUID().toString())
                            .customerId(createOrderRestModel.getCustomerId())
                            .items(createOrderRestModel.getItems())
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Ok";
    }

    public List<String> getOrders() {
        return Arrays.asList("");
    }

    @GetMapping("/order")
    public CreateOrderRestModel getOrder() {
        ArrayList<OrderItem> items = new ArrayList<OrderItem>();
        items.add(new OrderItem("1", 11));
        items.add(new OrderItem("2", 22));
        return CreateOrderRestModel.builder()
                .customerId(UUID.randomUUID().toString())
                .items(items)
                .build();
    }


}
