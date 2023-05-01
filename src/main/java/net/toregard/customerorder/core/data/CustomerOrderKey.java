package net.toregard.customerorder.core.data;

import lombok.*;

import java.io.Serializable;

@Data
public class CustomerOrderKey implements Serializable {

    //private static final long serialVersionUID = 7828986442335705057L;
    private String orderId;
    private String customerId;

    public CustomerOrderKey(String orderId, String customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
    }
}
