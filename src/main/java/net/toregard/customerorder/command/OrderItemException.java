package net.toregard.customerorder.command;

import net.toregard.customerorder.errorhandlig.RootException;

public class OrderItemException extends RootException {

    public OrderItemException(int id, String message) {
        super(id, message);
    }

    public OrderItemException(int id, String message, String description) {
        super(id, message, description);
    }


}
