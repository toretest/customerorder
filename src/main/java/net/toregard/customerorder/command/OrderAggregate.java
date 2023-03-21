package net.toregard.customerorder.command;

import net.toregard.customerorder.core.events.OrderCreatedEvent;
import net.toregard.customerorder.rest.OrderItem;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import org.springframework.beans.BeanUtils;

/**
 * Aggregate for this microservice!!!
 */
@Aggregate
public class OrderAggregate {
    /**
     * Help assosiate commandhandler with aggregate
     */
    @AggregateIdentifier
    private String OrderId;
    private String customerId;
    private java.util.List<OrderItem> items;

    /**
     * Needed when get all events when regenerate state by events
     */
    public OrderAggregate() {
    }

    /***
     * Handle CreateOrderCommand
     * @param createOrderCommand
     */
    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        if (createOrderCommand == null) throw new IllegalArgumentException("Null CreateOrderCommand");
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);
        //  @EventSourcingHandler
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    /**
     * Handle orderCreatedEvent (update)
     *
     * Only when after this data is applied to thos aggregate
     * it will send events to other event handlers
     *
     * Do not validate business logic here!
     */
    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
               this.OrderId = orderCreatedEvent.getOrderId();
               this.customerId = orderCreatedEvent.getCustomerId();
               this.items = orderCreatedEvent.getItems();
    }

}
