package net.toregard.customerorder.core.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "OrderEntity")
@Data
//@IdClass(CustomerOrderKey.class)
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = -5285190373781246219L;
    @Id
    private String orderId;
//    @Id
    private String customerId;
}
