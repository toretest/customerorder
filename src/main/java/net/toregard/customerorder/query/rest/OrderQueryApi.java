package net.toregard.customerorder.query.rest;

import lombok.extern.flogger.Flogger;
import net.toregard.customerorder.query.FindOrdersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/orders")
public class OrderQueryApi {
    private Logger logger = Logger.getLogger(OrderQueryApi.class.getName());
    private final QueryGateway queryGateway;

    public OrderQueryApi(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

     @GetMapping("/query/getOrders")
    public List<OrderRestModel> getOrders() {
        logger.info("OrderQueryApi.getOrders rest endpoint start");
        return queryGateway.
                query(
                        new FindOrdersQuery(),
                        ResponseTypes.multipleInstancesOf(OrderRestModel.class)).join();
    }
}
