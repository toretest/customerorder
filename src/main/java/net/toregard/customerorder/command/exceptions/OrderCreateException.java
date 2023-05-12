package net.toregard.customerorder.command.exceptions;

import net.toregard.customerorder.core.errorhandlig.OrderException;

public class OrderCreateException extends OrderException {
    public OrderCreateException(int id, String message) {
        super(id, message);
    }
    public OrderCreateException(int id, String message, String description) {
        super(id, message, description);
    }
}
