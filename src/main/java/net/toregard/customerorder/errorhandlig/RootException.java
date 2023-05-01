package net.toregard.customerorder.errorhandlig;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class RootException extends RuntimeException{
    private static final long serialVersionUID = -3932753680299368391L;
    private final int id;
    private final String description;

    protected RootException(int id, String message) {
         super(message);
         this.id = id;
        description = "";
    }
    protected RootException(int id, String message, String description) {
        super(message);
        this.id = id;
        this.description = description;
    }
}
