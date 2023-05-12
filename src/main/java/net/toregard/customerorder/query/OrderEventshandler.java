package net.toregard.customerorder.query;

import net.toregard.customerorder.core.data.OrderEntity;
import net.toregard.customerorder.core.data.OrderEntityRepository;
import net.toregard.customerorder.core.data.OrderItemEntity;
import net.toregard.customerorder.core.data.OrderItemEntityRepository;
import net.toregard.customerorder.core.events.OrderCreatedEvent;
import net.toregard.customerorder.core.events.OrderUpdateEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@ProcessingGroup("customerOrder-group")
public class OrderEventshandler {
private final OrderEntityRepository orderEntityRepository;
private final OrderItemEntityRepository orderItemEntityRepository;
    public OrderEventshandler(
            OrderEntityRepository orderEntityRepository,
            OrderItemEntityRepository orderItemEntityRepository
            ) {
        this.orderEntityRepository = orderEntityRepository;
        this.orderItemEntityRepository = orderItemEntityRepository;
    }

    @EventHandler
    @Transactional
    public void on(OrderCreatedEvent event) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(event, orderEntity);
        orderEntityRepository.save(orderEntity);
        event.getItems().forEach(item -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            // Link to order
            orderItemEntity.setOrderId(event.getOrderId());
            //Map all other properties
            BeanUtils.copyProperties(item, orderItemEntity);
            orderItemEntity.setOrderId(event.getOrderId());
            orderItemEntityRepository.save(orderItemEntity);
        });
    }

    @EventHandler
    @Transactional
    public void on(OrderUpdateEvent event) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(event, orderEntity);
        orderEntityRepository.save(orderEntity);
        event.getItems().forEach(item -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            // Link to order
            orderItemEntity.setOrderId(event.getOrderId());
            //Map all other properties
            BeanUtils.copyProperties(item, orderItemEntity);
            orderItemEntity.setOrderId(event.getOrderId());
            orderItemEntityRepository.save(orderItemEntity);
        });
    }
}
