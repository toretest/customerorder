package net.toregard.customerorder.query;

import net.toregard.customerorder.command.rest.OrderItem;
import net.toregard.customerorder.core.data.OrderEntityRepository;
import net.toregard.customerorder.core.data.OrderItemEntityRepository;
import net.toregard.customerorder.query.rest.OrderRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class FindOrdersQueryHandler {
private final OrderEntityRepository orderEntityRepository;
private final OrderItemEntityRepository orderItemEntityRepository;
    public FindOrdersQueryHandler(OrderEntityRepository orderEntityRepository,
                                  OrderItemEntityRepository orderItemEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
        this.orderItemEntityRepository = orderItemEntityRepository;
    }

    @QueryHandler
    public List<OrderRestModel> findOrders(FindOrdersQuery query) {
    List<OrderRestModel> ordersRest = new ArrayList<>();
    orderEntityRepository.findAll().forEach(orderEntity -> {
        OrderRestModel orderRest = new OrderRestModel();
        orderRest.setOrderId(orderEntity.getOrderId());
        orderRest.setCustomerId(orderEntity.getCustomerId());
        orderItemEntityRepository.findOrderItemsByOrderId(
                orderEntity.getOrderId()
                ).forEach(orderItemEntity -> orderRest.getOrderItems().add(
                        new OrderItem(
                                orderItemEntity.getOrderItemId(),
                                orderItemEntity.getQuantity(),
                                orderItemEntity.getName()
                        )
                ));
        ordersRest.add(orderRest);
    });
    return ordersRest;
    }

}
