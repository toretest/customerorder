package net.toregard.customerorder.command;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name="orderlookup")
public class OrderLookUpEntity {
    @Id
    private String orderId;
    private String customerId;
}
