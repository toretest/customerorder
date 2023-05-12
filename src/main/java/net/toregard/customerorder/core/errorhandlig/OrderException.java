package net.toregard.customerorder.core.errorhandlig;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class OrderException extends RuntimeException{
    private static final long serialVersionUID = -3932753680299368391L;
    private final int id;
    private final String description;

    protected OrderException(int id, String message) {
         super(message);
         this.id = id;
        description = "";
    }
    protected OrderException(int id, String message, String description) {
        super(message);
        this.id = id;
        this.description = description;
    }
}
