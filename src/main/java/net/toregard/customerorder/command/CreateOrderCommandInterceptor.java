package net.toregard.customerorder.command;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;
import java.util.logging.Logger;

/**
 * Interceptor for CreateOrderCommand
 *
 * Have to register it. In this code this
 */
@Component
public class CreateOrderCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private static final Logger logger =Logger.getLogger(CreateOrderCommandInterceptor.class.getName());
    /**
     * Intercepts a message right before it is dispatched to the Message Bus.
     *
     *
     * @param messages The Messages to pre-process
     *                  Input : <Integer=command index,CommandMessage
     * @return function that process messages
     *         Second CommandMessage<?> is the result of the processing
     * @throws Exception any exception that occurs during processing
     */
    @NotNull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@NotNull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            CreateOrderCommand createOrderCommand = (CreateOrderCommand) command.getPayload();
            if(CreateOrderCommand.class.equals(command.getPayload())){
              if (createOrderCommand.getItems() == null || createOrderCommand.getItems().isEmpty()) {
            final String errorMessage = "Missing item when creating order " + createOrderCommand.getOrderId();
            logger.info(errorMessage);
            throw new OrderItemException(1, errorMessage);
        }
            }
            return command;
        };

    }
}
