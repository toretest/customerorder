package net.toregard.customerorder.core.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "OrderItemEntity")
@Data
public class OrderItemEntity implements Serializable {
    private static final long serialVersionUID = -2249581713008093917L;
    @Id
    private String orderItemId;
    private String orderId;
    private Integer quantity;
    private String name;

    @Override
    public String toString() {
        return "OrderItemEntity{" +
                "orderItemId='" + orderItemId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                '}';
    }
}
