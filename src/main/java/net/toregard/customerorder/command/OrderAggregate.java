package net.toregard.customerorder.command;

import net.toregard.customerorder.core.events.OrderCreatedEvent;
import net.toregard.customerorder.command.rest.OrderItem;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.ApplyMore;
import org.axonframework.spring.stereotype.Aggregate;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Aggregate for this microservice!!!
 *
 * handle product state, Holding current state
 * handle command handles
 * handle business logic (for .example validation)
 * handle events
 */
@Aggregate
public class OrderAggregate {
    Logger logger = Logger.getLogger("OrderAggregate");
    /**
     * |
     * Help assosiate commandhandler with aggregate
     */
    @AggregateIdentifier
    private String OrderId;
    private String customerId;
    @AggregateMember
    private List<OrderItem> items = new ArrayList<>();

    /**
     * Needed when get all events when regenerate state by events
     */
    public OrderAggregate() {
    }

    /***
     * Handle CreateOrderCommand
     *
     * Command handle function. Register to the event bus og type CreateOrderCommand.
     *
     * Validation is done on interceptor
     *
     * Can validate here!
     * Create a event here!
     * @param createOrderCommand
     */
    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);
        //  Sent to event bus. Receiver: @EventSourcingHandler
          ApplyMore applyMore = AggregateLifecycle.apply(orderCreatedEvent);
          applyMore.andThen(() -> logger.info("OrderAggregate CommandHandler Order " + createOrderCommand.getOrderId()));
    }

    @CommandHandler
    public OrderAggregate(UpdateOrderCommand updateOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(updateOrderCommand, orderCreatedEvent);
        //  Sent to event bus. Receiver: @EventSourcingHandler
          ApplyMore applyMore = AggregateLifecycle.apply(orderCreatedEvent);
          applyMore.andThen(() -> logger.info("OrderAggregate CommandHandler Order " + updateOrderCommand.getOrderId()));
    }

    /**
     * on (NB! Standard convention)
     *
     * Only handle his own OrderCreatedEvent events
     *
     * Handle orderCreatedEvent (update)
     * <p>
     * Only update the aggregate state here!
     * <p>
     * Only when after this data is applied to thi aggregate
     * it will send events to other event handlers
     * <p>
     * Do not validate business logic here!
     */
    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        logger.info("EventSourcingHandler Order " + orderCreatedEvent.getOrderId());
        this.OrderId = orderCreatedEvent.getOrderId();
        this.customerId = orderCreatedEvent.getCustomerId();
        this.items.addAll(orderCreatedEvent.getItems());
    }

    @EventSourcingHandler
    public void on(UpdateOrderCommand updateOrderCommand) {
        logger.info("EventSourcingHandler Order " + updateOrderCommand.getOrderId());
        this.OrderId = updateOrderCommand.getOrderId();
        this.customerId = updateOrderCommand.getCustomerId();
        this.items.addAll(updateOrderCommand.getItems());
    }

}
