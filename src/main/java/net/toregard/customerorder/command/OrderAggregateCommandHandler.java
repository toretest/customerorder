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
public class OrderAggregateCommandHandler {
    Logger logger = Logger.getLogger("OrderAggregateCommandHandler");
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
    public OrderAggregateCommandHandler() {
    }

    /***
     * Handle CreateOrderCommand
     * Can validate here!
     * Create a event here!
     * @param createOrderCommand
     */
    @CommandHandler
    public OrderAggregateCommandHandler(CreateOrderCommand createOrderCommand) {
        if (createOrderCommand.getItems() == null || createOrderCommand.getItems().isEmpty()) {
            final String errorMessage = "Missing item when creating order " + createOrderCommand.getOrderId();
            logger.info(errorMessage);
            throw new OrderItemException(1, errorMessage);
        }
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);
        //  Sent to @EventSourcingHandler
          ApplyMore applyMore = AggregateLifecycle.apply(orderCreatedEvent);
          applyMore.andThen(() -> logger.info("OrderAggregateCommandHandler CommandHandler Order " + createOrderCommand.getOrderId()));
    }

    /**
     * on (NB! Standard convention)
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

}
