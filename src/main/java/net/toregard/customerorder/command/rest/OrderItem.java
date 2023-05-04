package net.toregard.customerorder.command.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private String orderItemId;
    private Integer quantity=null;
    private String name=null;
}
