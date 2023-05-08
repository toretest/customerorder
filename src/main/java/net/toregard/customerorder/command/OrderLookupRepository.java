package net.toregard.customerorder.command;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLookupRepository extends JpaRepository<OrderLookUpEntity,String> {
    OrderLookUpEntity findByOrderId(String orderId);
}
