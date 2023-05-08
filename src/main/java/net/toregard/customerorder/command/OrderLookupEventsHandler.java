package net.toregard.customerorder.command;

import net.toregard.customerorder.core.events.OrderCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * OrderLookupEventsHandler
 *
 * Is called by AggreateLifecycle.apply(orderCredEvent) in OrderAggregate.java
 *
 * This class is a side event handler for @see OrderEventHandler to save the order in a lookup table
 * All config together using the @ProcessingGroup("customerOrder-group") annotation
 *
 * ProcessingGroup added so it is handle only once and in same thread as other simular annotations
 * See also spring boot application yml
 * axon:
 *   eventhandling:
 *     processors:
 *         customerOrder-group:
 *             mode: subscribing
 *
 */
@Component
@ProcessingGroup("customerOrder-group")
public class OrderLookupEventsHandler {
    private final OrderLookupRepository orderLookupRepository;
    public OrderLookupEventsHandler(OrderLookupRepository orderLookupRepository) {
        this.orderLookupRepository = orderLookupRepository;
    }

    @EventHandler // This is the annotation that tells Axon that this method is an event handler.
    public void on(OrderCreatedEvent event) {
        OrderLookUpEntity orderLookUpEntity = new OrderLookUpEntity();
        orderLookUpEntity.setOrderId(event.getOrderId());
        orderLookUpEntity.setCustomerId(event.getCustomerId());
        orderLookupRepository.save(orderLookUpEntity);  // Save to the database
    }
}
