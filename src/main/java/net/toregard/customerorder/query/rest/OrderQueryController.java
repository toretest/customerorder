package net.toregard.customerorder.query.rest;

import net.toregard.customerorder.core.data.OrderEntity;
import net.toregard.customerorder.core.data.OrderEntityRepository;
import net.toregard.customerorder.query.FindOrdersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/orders")
public class OrderQueryController {
    private final QueryGateway queryGateway;

    public OrderQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<OrderRestModel> getOrdersByCustomerId(String customerId) {
        FindOrdersQuery findOrdersQuery = new FindOrdersQuery();
        List<OrderRestModel> orderRestModels = queryGateway.
                query(
                        findOrdersQuery,
                        ResponseTypes.multipleInstancesOf(OrderRestModel.class)).join();
        return orderRestModels;
    }
}
