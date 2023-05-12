package net.toregard.customerorder.command.exceptions;

import net.toregard.customerorder.core.errorhandlig.OrderException;

public class OrderUpdateException extends OrderException {
    public OrderUpdateException(int id, String message) {
        super(id, message);
    }
    public OrderUpdateException(int id, String message, String description) {
        super(id, message, description);
    }
}
