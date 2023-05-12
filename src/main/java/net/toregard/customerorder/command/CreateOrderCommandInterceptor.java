package net.toregard.customerorder.command;

import net.toregard.customerorder.command.exceptions.OrderCreateException;
import net.toregard.customerorder.command.exceptions.OrderUpdateException;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;
import java.util.logging.Logger;

/**
 * Interceptor for CreateOrderCommand
 * <p>
 * Have to register it. In this code this
 */
@Component
public class CreateOrderCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private OrderLookupRepository orderLookupRepository;
    private static final Logger logger = Logger.getLogger(CreateOrderCommandInterceptor.class.getName());

    public CreateOrderCommandInterceptor(OrderLookupRepository orderLookupRepository) {
        this.orderLookupRepository = orderLookupRepository;
    }

    /**
     * Intercepts a message right before it is dispatched to the Message Bus.
     *
     * @param messages The Messages to pre-process
     *                 Input : <Integer=command index,CommandMessage
     * @return function that process messages
     * Second CommandMessage<?> is the result of the processing
     * @throws Exception any exception that occurs during processing
     */
    @NotNull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@NotNull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            if (CreateOrderCommand.class.equals(command.getPayload())) {
                CreateOrderCommand createOrderCommand = (CreateOrderCommand) command.getPayload();
                OrderLookUpEntity orderLookUpEntity = orderLookupRepository.findByOrderId(createOrderCommand.getOrderId());
                if (orderLookUpEntity != null) {
                    final String errorMessage = "Duplicate orderId " + createOrderCommand.getOrderId();
                    logger.info(errorMessage);
                    throw new OrderCreateException(1, errorMessage);
                }
                if (createOrderCommand.getItems() == null || createOrderCommand.getItems().isEmpty()) {
                    final String errorMessage = "Missing item when creating order " + createOrderCommand.getOrderId();
                    logger.info(errorMessage);
                    throw new OrderCreateException(1, errorMessage);
                }
            }

            if (UpdateOrderCommand.class.equals(command.getPayload())) {
                UpdateOrderCommand updateOrderCommand = (UpdateOrderCommand) command.getPayload();
                OrderLookUpEntity orderLookUpEntity = orderLookupRepository.findByOrderId(updateOrderCommand.getOrderId());
                if (orderLookUpEntity == null) {
                    final String errorMessage = "Order does not exist for orderId " + updateOrderCommand.getOrderId();
                    logger.info(errorMessage);
                    throw new OrderUpdateException(2, errorMessage);
                }
            }
            return command;
        };
    }
}
