package net.toregard.customerorder.rest;

import com.google.common.collect.Ordering;
import com.google.common.io.LittleEndianDataInputStream;
import lombok.extern.flogger.Flogger;
import net.toregard.customerorder.commands.CreateOrderCommand;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/orders")
public class OrderController {

    Logger logger = Logger.getLogger("OrderController");

    /*
    Example
    {
    "customerId": "5a572f48-68b1-4f94-a17a-0fac9de782c8",
    "orderId": "6e6363fc-6458-49fa-a119-c4f4b9619de6",
    "orderItems": [
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
    public String createOrder(@RequestBody CreateOrderRestModel createOrderRestModel){
        logger.info(createOrderRestModel.toString());
        return "Ok";
    }

    public List<String> getOrders(){
        return Arrays.asList("");
    }

    @GetMapping("/order")
     public CreateOrderRestModel getOrder(){
         ArrayList<OrderItem> items= new ArrayList<OrderItem>();
       items.add(new OrderItem("1", 11));
        items.add(new OrderItem("2", 22));
        return CreateOrderRestModel.builder()
                .customerId(UUID.randomUUID().toString())
                .orderId(UUID.randomUUID().toString())
                .orderItems(items)
                .build();
    }


}
